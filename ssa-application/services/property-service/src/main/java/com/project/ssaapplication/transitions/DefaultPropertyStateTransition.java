package com.project.ssaapplication.transitions;

import org.springframework.stereotype.Service;

@Service
public class DefaultPropertyStateTransition implements PropertyStateTransition {

    @Override
    public boolean isFullyFunded(double fundedAmount, double totalPrice) {
        return fundedAmount >= totalPrice;
    }

    @Override
    public boolean checkFundingDeadline(long currentTime, long fundingDeadline) {
        return currentTime > fundingDeadline;
    }

    @Override
    public String transitionState(String currentState, boolean fullyFunded, boolean fundingExpired) {
        if (fullyFunded) {
            return "FUNDED";
        } else if (fundingExpired) {
            return "EXPIRED";
        }
        return currentState;
    }
}