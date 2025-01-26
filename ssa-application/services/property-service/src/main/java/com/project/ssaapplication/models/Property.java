package com.project.ssaapplication.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type; // Apartment, Building, etc.
    private double price;
    private Date fundingDeadline;
    private String fundingStatus; // OPEN, FUNDED, CLOSED, etc.

    public Property() {
    }

    public Property(String name, String type, double price, Date fundingDeadline, String fundingStatus) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.fundingDeadline = fundingDeadline;
        this.fundingStatus = fundingStatus;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
