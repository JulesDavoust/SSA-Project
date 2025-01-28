package com.project.ssaapplication.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID property;

    private UUID userId;

    private double sharePercentage;

    private double investmentAmount;

    public Investment() {}

    public Investment(Property property, UUID user, double investmentAmount) {
        this.property = property.getId();
        this.userId = user;
        this.investmentAmount = investmentAmount;
        this.sharePercentage = calculateSharePercentage(property.getPrice(), investmentAmount);
    }

    // Calculate share percentage
    private double calculateSharePercentage(double propertyPrice, double investmentAmount) {
        return (investmentAmount / propertyPrice) * 100;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProperty() {
        return property;
    }

    public void setProperty(UUID property) {
        this.property = property;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID user) {
        this.userId = user;
    }

    public double getSharePercentage() {
        return sharePercentage;
    }

    public void setSharePercentage(double sharePercentage) {
        this.sharePercentage = sharePercentage;
    }

    public double getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(double investmentAmount) {
        this.investmentAmount = investmentAmount;
    }
}