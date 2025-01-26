package com.project.ssaapplication.channels;

import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationChannel {

    @Override
    public void sendNotification(String recipient, String subject, String message) {
        // Simule l'envoi d'un email
        System.out.println("Email envoyé à : " + recipient);
        System.out.println("Sujet : " + subject);
        System.out.println("Message : " + message);
    }
}
