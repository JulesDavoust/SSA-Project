package com.project.ssaapplication.strategies;

public interface PropertyCalculationStrategy {
    double calculateRentalIncome(double propertyPrice, double rentalIncomePercentage);

    double calculateAppreciation(double propertyPrice, double appreciationPercentage);
}