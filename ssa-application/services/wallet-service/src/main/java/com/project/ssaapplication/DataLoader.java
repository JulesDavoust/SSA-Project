// package com.project.ssaapplication;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
// import com.project.ssaapplication.models.Wallet;
// import com.project.ssaapplication.repositories.WalletRepository;

// @Component
// public class DataLoader implements CommandLineRunner {

//     private final WalletRepository walletRepository;

//     @Autowired
//     public DataLoader(WalletRepository walletRepository) {
//         this.walletRepository = walletRepository;
//     }

//     @Override
//     public void run(String... args) throws Exception {
//         walletRepository.save(new Wallet("Investor1", 10000));
//         walletRepository.save(new Wallet("Investor2", 20000));
//     }
// }
