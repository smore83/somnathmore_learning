package com.backend.demo.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Data
@Builder
public class ErrorResponse {
    private String message;
    private String title;
    private Long timestamp;
    private int status;

}
