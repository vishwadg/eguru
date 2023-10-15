package com.tutor.notificationservice.service.impl;

import com.tutor.notificationservice.configs.EmailConfig;
import com.tutor.notificationservice.entity.Response;
import com.tutor.notificationservice.entity.Email;
import com.tutor.notificationservice.service.TutorEmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service // Annotates this class as a Spring service.
public class TutorEmailNotificationImpl implements TutorEmailNotificationService {

    // Injects an instance of the EmailConfig.
    @Autowired
    private EmailConfig emailConfig;

    // Implements the sendEmail method from the TutorEmailNotificationService interface.
    @Override
    public Response sendEmail(Email email) {

        // Create a Response object to store the result of sending the email.
        Response response = new Response();
        try {
            // Create a SimpleMailMessage to configure the email.
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(email.getTo());
            simpleMailMessage.setSubject(email.getSubject());
            simpleMailMessage.setText(email.getText());

            // Use the JavaMailSender from EmailConfig to send the email.
            emailConfig.getJavaMailSender().send(simpleMailMessage);
            response.setCode(0);
            response.setMessage("Email sent Ok");
        } catch (Exception e) {
            // Set the response code and message for an error during email sending.
            response.setCode(1);
            response.setMessage("Error on sending email" + response.getMessage());
        }
        return response;
    }
}