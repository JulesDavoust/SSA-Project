package com.project.ssaapplication.controllers;

import com.project.ssaapplication.models.Wallet;
import com.project.ssaapplication.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWallet(@PathVariable UUID id) {
        return ResponseEntity.ok(walletService.getWallet(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Wallet>> getWallets() {
        return ResponseEntity.ok(walletService.getWallets());
    }

    @PostMapping("/{id}/credit")
    public ResponseEntity<Void> creditWallet(@PathVariable UUID id, @RequestParam double amount) {
        walletService.creditWallet(id, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/debit")
    public ResponseEntity<Void> debitWallet(@PathVariable UUID id, @RequestParam double amount) {
        try {
            walletService.debitWallet(id, amount);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
