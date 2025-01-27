package com.project.ssaapplication.services;

import org.springframework.stereotype.Service;
import com.project.ssaapplication.channels.NotificationChannel;

@Service
public class IncomeNotificationService implements NotificationChannel {

    @Override
    public void sendNotification(String recipientEmail, String subject, String content) {
        // Simule l'envoi d'un email
        System.out.println("==================== Income Notification Service ====================");
        System.out.println("Income Notification Sent to: " + recipientEmail);
        System.out.println("Subject: " + subject);
        System.out.println("Content: " + content);
        System.out.println("======================================================================");
    }
}
