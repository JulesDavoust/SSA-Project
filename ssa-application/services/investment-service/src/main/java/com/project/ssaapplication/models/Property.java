package com.project.ssaapplication.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Property {

    @Id
    private UUID id;
    private String name;
    private String type;
    private double price;
    private Date fundingDeadline;
    private String fundingStatus;
    private double rentalIncomePercentage;
    private double appreciationPercentage;

    public Property() {}

    public Property(UUID id, String name, String type, double price, Date fundingDeadline, String fundingStatus, double rentalIncomePercentage, double appreciationPercentage) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.fundingDeadline = fundingDeadline;
        this.fundingStatus = fundingStatus;
        this.rentalIncomePercentage = rentalIncomePercentage;
        this.appreciationPercentage = appreciationPercentage;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getFundingDeadline() {
        return fundingDeadline;
    }

    public void setFundingDeadline(Date fundingDeadline) {
        this.fundingDeadline = fundingDeadline;
    }

    public String getFundingStatus() {
        return fundingStatus;
    }

    public void setFundingStatus(String fundingStatus) {
        this.fundingStatus = fundingStatus;
    }

    public double getRentalIncomePercentage() {
        return rentalIncomePercentage;
    }

    public void setRentalIncomePercentage(double rentalIncomePercentage) {
        this.rentalIncomePercentage = rentalIncomePercentage;
    }

    public double getAppreciationPercentage() {
        return appreciationPercentage;
    }

    public void setAppreciationPercentage(double appreciationPercentage) {
        this.appreciationPercentage = appreciationPercentage;
    }
}