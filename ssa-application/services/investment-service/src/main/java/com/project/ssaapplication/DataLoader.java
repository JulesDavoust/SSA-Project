package com.project.ssaapplication;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.project.ssaapplication.models.Investment;
import com.project.ssaapplication.models.Property;
import com.project.ssaapplication.models.Wallet;
import com.project.ssaapplication.repositories.InvestmentRepository;
import com.project.ssaapplication.services.PropertyServiceClient;
import com.project.ssaapplication.services.WalletServiceClient;

@Component
public class DataLoader implements CommandLineRunner {

    private final InvestmentRepository  investmentRepository;

    private final PropertyServiceClient propertyServiceClient;

    private final WalletServiceClient walletServiceClient;

    @Autowired
    public DataLoader(InvestmentRepository  investmentRepository, PropertyServiceClient propertyServiceClient, WalletServiceClient walletServiceClient) {
        this.investmentRepository = investmentRepository;
        this.propertyServiceClient = propertyServiceClient;
        this.walletServiceClient = walletServiceClient;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Wallet> users = walletServiceClient.getWallets().getBody();
        if (users.size() <= 0) { throw new Exception("No wallet found"); }
        UUID user1 = users.get(0).getId();
        UUID user2 = users.get(1).getId();

        List<Property> properties = propertyServiceClient.getAllProperties().getBody();
        if (properties.size() <= 0) { throw new Exception("No properties found"); }

        investmentRepository.save(new Investment(
            properties.get(0),
            user1,
            1000));

        investmentRepository.save(new Investment(
            properties.get(1),
            user1,
            207000));
        investmentRepository.save(new Investment(
            properties.get(1),
            user2,
            90000));
        investmentRepository.save(new Investment(
            properties.get(2),
            user1,
            35357));
    }
}
