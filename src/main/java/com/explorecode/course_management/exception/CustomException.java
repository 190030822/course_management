package com.explorecode.course_management.exception;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomException {
    
    private LocalDateTime timeStamp;
    private int statusCode;
    private String error;
    private Map<String, String> message;
}
