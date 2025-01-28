package com.project.ssaapplication.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ssaapplication.models.Wallet;

@Service
@FeignClient(name = "wallet-service", url = "http://localhost:8081")
public interface WalletServiceClient {
    @GetMapping("/wallets/{walletId}")
    ResponseEntity<Wallet> getWallet(@PathVariable("walletId") String walletId);

    @GetMapping("/wallets/")
    ResponseEntity<List<Wallet>> getWallets();
    
    @PostMapping("/wallets/{id}/debit")
    ResponseEntity<Void> debitWallet(@PathVariable("id") String id, @RequestParam("amount") double amount);
}

