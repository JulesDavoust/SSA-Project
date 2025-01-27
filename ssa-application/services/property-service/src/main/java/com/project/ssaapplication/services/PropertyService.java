package com.project.ssaapplication.services;

import com.project.ssaapplication.models.Property;
import com.project.ssaapplication.repositories.PropertyRepository;
import com.project.ssaapplication.strategies.PropertyCalculationStrategy;
import com.project.ssaapplication.transitions.PropertyStateTransition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyCalculationStrategy calculationStrategy;

    @Autowired
    private PropertyStateTransition stateTransition;

    public List<Property> listOpenForFunding() {
        return propertyRepository.findByFundingStatus("OPEN");
    }

    public List<Property> listAllProperties() {
        return propertyRepository.findAll();
    }

    public Optional<Property> getPropertyById(UUID propertyId) {
        return propertyRepository.findById(propertyId);
    }

    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Property updateProperty(UUID propertyId, Property updatedProperty) {
        return propertyRepository.findById(propertyId).map(property -> {
            property.setName(updatedProperty.getName());
            property.setType(updatedProperty.getType());
            property.setPrice(updatedProperty.getPrice());
            property.setFundingDeadline(updatedProperty.getFundingDeadline());
            property.setFundingStatus(updatedProperty.getFundingStatus());
            property.setRentalIncomePercentage(updatedProperty.getRentalIncomePercentage());
            property.setAppreciationPercentage(updatedProperty.getAppreciationPercentage());
            return propertyRepository.save(property);
        }).orElseThrow(() -> new RuntimeException("Property not found"));
    }

    public void deleteProperty(UUID propertyId) {
        propertyRepository.deleteById(propertyId);
    }

    public double calculateRentalIncome(UUID propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        return calculationStrategy.calculateRentalIncome(property.getPrice(), property.getRentalIncomePercentage());
    }

    public double calculateAppreciation(UUID propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        return calculationStrategy.calculateAppreciation(property.getPrice(), property.getAppreciationPercentage());
    }

    public String checkAndTransitionState(UUID propertyId, double fundedAmount) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        boolean fullyFunded = stateTransition.isFullyFunded(fundedAmount, property.getPrice());
        boolean fundingExpired = stateTransition.checkFundingDeadline(new Date().getTime(), property.getFundingDeadline().getTime());
        String newState = stateTransition.transitionState(property.getFundingStatus(), fullyFunded, fundingExpired, fundedAmount);
        property.setFundingStatus(newState);
        propertyRepository.save(property);
        return newState;
    }
}
