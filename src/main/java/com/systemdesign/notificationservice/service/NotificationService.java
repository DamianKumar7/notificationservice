package com.systemdesign.notificationservice.service;

import com.systemdesign.notificationservice.DTO.Event;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {

    public void sendNotification(Event event);

    String processNotification(Event event);

    void sendEmail(String emailBody, String userEmail);
}
