package com.systemdesign.notificationservice.service;

import com.systemdesign.notificationservice.DTO.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {

    @Autowired
    KafkaTemplate<String,Event> kafkaTemplate;


    @Value("${spring.kafka.topic}")
    private String kafkaTopic;

    public void pushMessageToQueue(Event event) {
        kafkaTemplate.send(kafkaTopic,event);
    }
}
