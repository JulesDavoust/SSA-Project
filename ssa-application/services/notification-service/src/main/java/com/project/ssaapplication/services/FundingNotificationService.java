package com.project.ssaapplication.services;

import org.springframework.stereotype.Service;
import com.project.ssaapplication.channels.NotificationChannel;

@Service
public class FundingNotificationService implements NotificationChannel {

    @Override
    public void sendNotification(String recipientEmail, String subject, String content) {
        // Simule l'envoi d'un email
        System.out.println("==================== Funding Notification Service ====================");
        System.out.println("Funding Notification Sent to: " + recipientEmail);
        System.out.println("Subject: " + subject);
        System.out.println("Content: " + content);
        System.out.println("======================================================================");
    }
}
