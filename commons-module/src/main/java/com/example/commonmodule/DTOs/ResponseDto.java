package com.example.commonmodule.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The type Response dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ResponseDto {
    private int code;
    private String message;
}
