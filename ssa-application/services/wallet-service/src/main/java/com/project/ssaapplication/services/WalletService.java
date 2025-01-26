package com.project.ssaapplication.services;

import com.project.ssaapplication.models.Wallet;
import com.project.ssaapplication.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public Wallet getWallet(UUID walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    public void creditWallet(UUID walletId, double amount) {
        Wallet wallet = getWallet(walletId);
        wallet.credit(amount);
        walletRepository.save(wallet);
    }

    public void debitWallet(UUID walletId, double amount) {
        Wallet wallet = getWallet(walletId);
        wallet.debit(amount);
        walletRepository.save(wallet);
    }
}
