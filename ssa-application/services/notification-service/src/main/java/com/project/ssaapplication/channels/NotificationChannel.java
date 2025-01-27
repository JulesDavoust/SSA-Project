package com.project.ssaapplication.channels;

public interface NotificationChannel {
    void sendNotification(String recipientEmail, String subject, String content);
}