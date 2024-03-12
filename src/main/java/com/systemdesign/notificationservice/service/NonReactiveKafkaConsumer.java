package com.systemdesign.notificationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemdesign.notificationservice.DTO.Event;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NonReactiveKafkaConsumer {

    @Autowired
    NotificationService notificationService;

    @KafkaListener(topics = {"notification-events"})
    public void onMessage(String consumerRecord) {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("successfully consumed from the topic the message ",consumerRecord);
        try{
            Event event = objectMapper.readValue(consumerRecord, Event.class);
            notificationService.sendNotification(event);
        }
        catch(Exception ex){
            log.info("exception occurred. This is manual log");
            log.error(String.valueOf(ex));
        }
    }
}
