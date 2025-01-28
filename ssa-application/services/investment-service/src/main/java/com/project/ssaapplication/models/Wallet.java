package com.project.ssaapplication.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Wallet {

    @Id
    private UUID id;

    private double balance;

    public Wallet() {}

    public Wallet(UUID id, double balance) {
        this.id = id;
        this.balance = balance;
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
