package com.project.ssaapplication.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ssaapplication.models.Investment;
import com.project.ssaapplication.models.Property;
import com.project.ssaapplication.models.Wallet;
import com.project.ssaapplication.repositories.InvestmentRepository;
import com.project.ssaapplication.validations.InvestmentValidation;

@Service
public class InvestmentService {
    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private WalletServiceClient walletServiceClient;

    @Autowired
    private InvestmentValidation investmentValidation;

    public boolean allocateInvestment(UUID user, Property property, double amount) {
        // Validate user role (but no user yet)
        if (!property.getFundingStatus().equals("OPEN")) {
            throw new IllegalArgumentException("Property is not open for funding.");
        }

        // The balance would be handled by the User service be since it is not implemented yet lets do it ourselves
        // WARNING - Would not be done here if all the services were implemented
        Wallet userWallet = walletServiceClient.getWallet(user.toString()).getBody();

        if (userWallet.getBalance() - amount < 0) {
            throw new IllegalArgumentException("You don't have enough money to invest that much !");
        } else {
            walletServiceClient.debitWallet(user.toString(), amount);
        }
        
        Investment investment = new Investment(property, user, amount);
        if (investmentValidation.validateInvestment(investment) && validateInvestmentLimits(user, amount)) {
            investmentRepository.save(investment);
        } else {
            throw new IllegalArgumentException("Not enough porperty shares available or to high amount !");
        }
        
        return true;
    }

    public List<Investment> listInvestmentsByUser(UUID userId) {
        return investmentRepository.findByUserId(userId);
    }

    public List<Investment> listAllInvestments () {
        return investmentRepository.findAll();
    }

    public boolean validateInvestmentLimits(UUID user, double amount) {
        // It would be retrieve from the user.getWallet() since the wallet keep track of the transactions and the transactions
        // keep track of the date but user service not implemented yet
        Wallet wallet = walletServiceClient.getWallet(user.toString()).getBody();

        // So we will only use the total of all the investments without taking the year timestamp limit in account for test purposes
        // From the wallet retrieve the total amount of transaction in the year
        List<Investment> investments = investmentRepository.findByUserId(user); // Get all the investments ever done
        double totalTransactionInYear = 0; // Name not accurate in the current context of course
        for (Investment investment : investments) {
            totalTransactionInYear += investment.getInvestmentAmount();
        }

        return 500 < totalTransactionInYear + amount && totalTransactionInYear + amount <= 100000;
    }
}