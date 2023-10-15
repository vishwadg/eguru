package com.tutor.notificationservice.service.impl;

import com.tutor.notificationservice.entity.Email;
import com.tutor.notificationservice.entity.Response;
import com.tutor.notificationservice.service.TutorEmailNotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest()
class TutorEmailNotificationImplTest {
    @Autowired
    private TutorEmailNotificationService notificationService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void sendEmail() {
        Email email = new Email("eme2119@gmail.com", "Hi", "This is from tutor finder");
        Response response = notificationService.sendEmail(email);
        assertThat(response.getCode())
                .isEqualTo(0);
    }

}
