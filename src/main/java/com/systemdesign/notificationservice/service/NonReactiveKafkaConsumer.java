package com.systemdesign.notificationservice.service;

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

    @KafkaListener(topics = {""})
    public void onMessage(Event event,Acknowledgment ack) {
        log.info("successfully consumed from the topic the message for user id : %s",event.getUserId());
        try{
            notificationService.sendNotification(event);
        }
        catch(Exception ex){
            log.error(String.valueOf(ex));
        }
        finally{
            ack.acknowledge();
        }
    }
}
