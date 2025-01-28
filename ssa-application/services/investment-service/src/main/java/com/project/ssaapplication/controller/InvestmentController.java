package com.project.ssaapplication.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ssaapplication.models.Investment;
import com.project.ssaapplication.models.InvestmentRequest;
import com.project.ssaapplication.services.InvestmentService;
import com.project.ssaapplication.services.PropertyServiceClient;

@RestController
@RequestMapping("/investments")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @Autowired
    private PropertyServiceClient propertyServiceClient;

    @PostMapping("/allocate")
    public ResponseEntity<String> allocateInvestment(@RequestBody InvestmentRequest request) {
        try {
            boolean success = investmentService.allocateInvestment(request.getUser(), propertyServiceClient.getPropertyById(request.getProperty().toString()).getBody(), request.getAmount());
            if (success) {
                return ResponseEntity.ok("Investment allocated successfully.");
            } else {
                return ResponseEntity.badRequest().body("Investment allocation failed.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<Investment>> listAllInvestments () {
        List<Investment> investments = investmentService.listAllInvestments();
        return ResponseEntity.ok(investments);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Investment>> listInvestmentsByUser(@PathVariable UUID userId) {
        List<Investment> investments = investmentService.listInvestmentsByUser(userId);
        return ResponseEntity.ok(investments);
    }

    @GetMapping("/allocatable/{userId}")
    public ResponseEntity<Boolean> validateInvestmentLimits(@PathVariable UUID userId, @RequestParam double amount) {
        boolean canInvest = investmentService.validateInvestmentLimits(userId, amount);
        return ResponseEntity.ok(canInvest);
    }
}
