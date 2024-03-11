package com.systemdesign.notificationservice.service;

import com.systemdesign.notificationservice.DTO.Event;
import com.systemdesign.notificationservice.repository.UserDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    UserDataDao userDataDao;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void sendNotification(Event event) {
        String userEmail = userDataDao.getUserEmailFromId(event.getUserId());
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
        javaMailSender.send(simpleMailMessage);

    }
}
