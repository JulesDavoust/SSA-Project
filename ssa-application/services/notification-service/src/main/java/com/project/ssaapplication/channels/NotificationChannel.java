package com.project.ssaapplication.channels;

public interface NotificationChannel {
    void sendNotification(String recipient, String subject, String message);
}
