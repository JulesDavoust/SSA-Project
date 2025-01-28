package com.project.ssaapplication.validations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ssaapplication.models.Investment;
import com.project.ssaapplication.models.Property;
import com.project.ssaapplication.repositories.InvestmentRepository;
import com.project.ssaapplication.services.PropertyServiceClient;

@Service
public class DefaultInvestmentValidation implements InvestmentValidation {
    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private PropertyServiceClient propertyServiceClient;

    @Override
    public boolean validateInvestment(Investment investment) {
        Property property = propertyServiceClient.getPropertyById(investment.getProperty().toString()).getBody();
        List<Investment> investments = investmentRepository.findByProperty(property.getId());
        double alreadyOwned = 0;
        for (Investment investmentDone : investments) {
            alreadyOwned += investmentDone.getSharePercentage();
        }

        return alreadyOwned + investment.getSharePercentage() <= 100;
    }
}
