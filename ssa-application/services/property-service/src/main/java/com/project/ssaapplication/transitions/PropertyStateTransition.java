package com.project.ssaapplication.transitions;

public interface PropertyStateTransition {
    boolean isFullyFunded(double fundedAmount, double totalPrice);

    boolean checkFundingDeadline(long currentTime, long fundingDeadline);

    String transitionState(String currentState, boolean fullyFunded, boolean fundingExpired, double amount);
}