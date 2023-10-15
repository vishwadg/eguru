package com.tutor.notificationservice.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration // Annotates this class as a Spring configuration class.
public class EmailConfig {

    // Injects values from application.properties or application.yml.
    @Value("${spring.mail.host}")
    private String mailServerHost;

    @Value("${spring.mail.port}")
    private Integer mailServerPort;

    @Value("${spring.mail.username}")
    private String mailServerUsername;

    @Value("${spring.mail.password}")
    private String mailServerPassword;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String mailServerAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String mailServerStartTls;

    // Defines a bean for JavaMailSender, used for sending email.
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailServerHost);
        javaMailSender.setPort(mailServerPort);
        javaMailSender.setUsername(mailServerUsername);
        javaMailSender.setPassword(mailServerPassword);

        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", mailServerAuth);
        properties.put("mail.smtp.starttls.enable", mailServerStartTls);
        properties.put("mail.debug", "true");

        return javaMailSender;
    }
}
