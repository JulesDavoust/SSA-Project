package com.project.ssaapplication.transitions;

import com.project.ssaapplication.services.IncomeNotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultPropertyStateTransition implements PropertyStateTransition {

    @Autowired
    private IncomeNotificationService incomeNotificationService;

    @Override
    public boolean isFullyFunded(double fundedAmount, double totalPrice) {
        return fundedAmount >= totalPrice;
    }

    @Override
    public boolean checkFundingDeadline(long currentTime, long fundingDeadline) {
        return currentTime > fundingDeadline;
    }

    @Override
    public String transitionState(String currentState, boolean fullyFunded, boolean fundingExpired, double amount) {
        if (fullyFunded) {
            incomeNotificationService.sendNotification("investor@example.com", "Property Fully Funded", "The property has been fully funded and is now active.");
            return "FUNDED";
        } else if (fundingExpired) {
            incomeNotificationService.sendNotification("investor@example.com", "Property Fully Expired", "he property funding has expired, and your funds will be refunded.");
            return "EXPIRED";
        }
        return currentState;
    }
}