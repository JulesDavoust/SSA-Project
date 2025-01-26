package com.project.ssaapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.ssaapplication.models.Property;
import com.project.ssaapplication.repositories.PropertyRepository;

import java.util.List;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> listOpenForFunding() {
        // Retourne toutes les propriétés avec un statut "OPEN"
        return propertyRepository.findByFundingStatus("OPEN");
    }

    public Property addProperty(Property property) {
        // Définit le statut initial comme "OPEN" et enregistre la propriété
        property.setFundingStatus("OPEN");
        return propertyRepository.save(property);
    }

    public Property updateProperty(Long id, Property updatedProperty) {
        // Met à jour une propriété existante
        return propertyRepository.findById(id).map(property -> {
            property.setName(updatedProperty.getName());
            property.setType(updatedProperty.getType());
            property.setPrice(updatedProperty.getPrice());
            property.setFundingDeadline(updatedProperty.getFundingDeadline());
            property.setFundingStatus(updatedProperty.getFundingStatus());
            return propertyRepository.save(property);
        }).orElseThrow(() -> new RuntimeException("Property not found"));
    }

    public void deleteProperty(Long id) {
        // Supprime une propriété par son ID
        propertyRepository.deleteById(id);
    }
}
