package com.example.commonmodule.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The NotificationDto class represents a data transfer object for notification-related information.
 * It includes fields such as the recipient, subject, and message text.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationDto {
    private String to;
    private String subject;
    private String text;
}

