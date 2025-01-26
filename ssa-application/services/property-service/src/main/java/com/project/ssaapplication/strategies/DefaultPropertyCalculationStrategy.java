package com.project.ssaapplication.strategies;

import org.springframework.stereotype.Service;

@Service
public class DefaultPropertyCalculationStrategy implements PropertyCalculationStrategy {

    @Override
    public double calculateRentalIncome(double propertyPrice, double rentalIncomePercentage) {
        return propertyPrice * (rentalIncomePercentage / 100);
    }

    @Override
    public double calculateAppreciation(double propertyPrice, double appreciationPercentage) {
        return propertyPrice * (appreciationPercentage / 100);
    }
}