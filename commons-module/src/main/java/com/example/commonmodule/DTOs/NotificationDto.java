package com.example.commonmodule.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The type Notification dto.
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
