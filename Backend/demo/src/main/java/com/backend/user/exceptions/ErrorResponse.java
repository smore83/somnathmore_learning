package com.backend.user.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private String message;
    private String title;
    private Long timestamp;

}
