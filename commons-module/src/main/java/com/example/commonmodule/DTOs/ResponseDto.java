package com.example.commonmodule.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The ResponseDto class represents a data transfer object for API responses.
 * It includes fields for response code and message.
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ResponseDto {
    private int code;
    private String message;
}

