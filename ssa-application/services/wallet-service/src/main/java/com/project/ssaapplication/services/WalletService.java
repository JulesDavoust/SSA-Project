package com.project.ssaapplication.services;

import com.project.ssaapplication.models.Wallet;
import com.project.ssaapplication.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private FundingNotificationService fundingNotificationService;

    public Wallet getWallet(UUID walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    public List<Wallet> getWallets() {
        return walletRepository.findAll();
    }

    public void creditWallet(UUID walletId, double amount) {
        Wallet wallet = getWallet(walletId);
        wallet.credit(amount);
        walletRepository.save(wallet);

        fundingNotificationService.sendNotification(
            "user@domain.com", // Remplacez par l'email réel de l'utilisateur
            "Crédit sur portefeuille",
            "Un montant de " + amount + " a été crédité sur votre portefeuille."
        );
    }

    public void debitWallet(UUID walletId, double amount) {
        Wallet wallet = getWallet(walletId);
        wallet.debit(amount);
        walletRepository.save(wallet);

        fundingNotificationService.sendNotification(
            "user@domain.com", // Remplacez par l'email réel de l'utilisateur
            "Débit sur portefeuille",
            "Un montant de " + amount + " a été débité sur votre portefeuille."
        );
    }
}
