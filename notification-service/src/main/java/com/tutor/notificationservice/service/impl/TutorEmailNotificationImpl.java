package com.tutor.notificationservice.service.impl;

import com.tutor.notificationservice.configs.EmailConfig;
import com.tutor.notificationservice.entity.Response;
import com.tutor.notificationservice.entity.Email;
import com.tutor.notificationservice.service.TutorEmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class TutorEmailNotificationImpl implements TutorEmailNotificationService {

    @Autowired
    private EmailConfig emailConfig;

    @Override
    public Response sendEmail(Email email) {
        Response response = new Response();
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(email.getTo());
            simpleMailMessage.setSubject(email.getSubject());
            simpleMailMessage.setText(email.getText());

            emailConfig.getJavaMailSender().send(simpleMailMessage);
            response.setCode(0);
            response.setMessage("Email sent Ok");
        } catch (Exception e) {
            response.setCode(1);
            response.setMessage("Error on sending email" + response.getMessage());
        }
        return response;
    }
}