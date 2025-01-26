// package com.project.ssaapplication;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
// import com.project.ssaapplication.models.Investment;
// import com.project.ssaapplication.repositories.InvestmentRepository;

// @Component
// public class DataLoader implements CommandLineRunner {

//     private final InvestmentRepository  investmentRepository;

    // @Autowired
    // public DataLoader(InvestmentRepository  investmentRepository) {
    //     this.investmentRepository = investmentRepository;
    // }

//     @Override
//     public void run(String... args) throws Exception {
//         investmentRepository.save(new Investment("Investor1", 5000, "Property1"));
//         investmentRepository.save(new Investment("Investor2", 10000, "Property2"));
//     }
// }
