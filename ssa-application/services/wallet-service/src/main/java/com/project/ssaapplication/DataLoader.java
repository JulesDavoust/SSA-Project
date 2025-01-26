package com.project.ssaapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.project.ssaapplication.models.Wallet;
import com.project.ssaapplication.repositories.WalletRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final WalletRepository walletRepository;

    @Autowired
    public DataLoader(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Wallet wallet1 = new Wallet();
        wallet1.setBalance(10000);
        walletRepository.save(wallet1);

        Wallet wallet2 = new Wallet();
        wallet2.setBalance(5000);
        walletRepository.save(wallet2);
    }
}
