package com.systemdesign.notificationservice.controller;

import com.systemdesign.notificationservice.DTO.Event;
import com.systemdesign.notificationservice.service.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/v1/notification")
public class NotificationController {

    @Autowired
    EventProducer eventProducer;

    @PostMapping("/send")
    public ResponseEntity<?> sendNotification(@RequestBody Event event){
        eventProducer.pushMessageToQueue(event);
        return ResponseEntity.ok("Notification Request has been sent");
    }
}
