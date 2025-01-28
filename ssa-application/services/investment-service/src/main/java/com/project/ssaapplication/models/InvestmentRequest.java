package com.project.ssaapplication.models;

import java.util.UUID;

public class InvestmentRequest {
    private String user;
    private String property;
    private double amount;

    public UUID getUser() {
        return UUID.fromString(user);
    }

    public void setUser(String user) {
        this.user = user;
    }

    public UUID getProperty() {
        return UUID.fromString(property);
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
