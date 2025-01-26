package com.project.ssaapplication.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private double balance;

    public Wallet() {
        this.balance = 0.0; // Initial balance
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void credit(double amount) {
        this.balance += amount;
    }

    public void debit(double amount) {
        if (amount > this.balance) {
            throw new RuntimeException("Insufficient balance");
        }
        this.balance -= amount;
    }
}
