package com.project.ssaapplication.services;

import com.project.ssaapplication.channels.NotificationChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationChannel emailNotificationService;

    public void notifyByEmail(String recipient, String subject, String message) {
        emailNotificationService.sendNotification(recipient, subject, message);
    }
}
