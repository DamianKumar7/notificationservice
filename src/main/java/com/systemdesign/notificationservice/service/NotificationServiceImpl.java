package com.systemdesign.notificationservice.service;

import com.systemdesign.notificationservice.DTO.Event;
import com.systemdesign.notificationservice.repository.UserDataDao;
import com.systemdesign.notificationservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void  sendNotification(Event event) {
        String userEmail = userRepository.findEmailByUserId(event.getUserId());
        String emailBody = processNotification(event);
        sendEmail(emailBody,userEmail);
    }

    @Override
    public String processNotification(Event event) {
        return "hello user , here is your sample mail of type "+ event.getEventType().getStringValue();
    }

    @Override
    public void sendEmail(String emailBody,String userEmail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(userEmail);
        simpleMailMessage.setSubject("sample");
        simpleMailMessage.setText(emailBody);
        try{
            javaMailSender.send(simpleMailMessage);
            log.info("email successfully sent");
        }
        catch (Exception e){
            log.info("exception occured");
            log.error(String.valueOf(e));
        }


    }
}
