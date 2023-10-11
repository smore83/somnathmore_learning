package com.sso.ssointegration.exception;


import com.sso.ssointegration.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RecordNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    private ResponseEntity<ErrorResponse> handleNotFoundExceptions(Exception exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(System.currentTimeMillis())
                .status(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .title(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();

        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .headers(responseHeaders)
                .body(errorResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        String errorMessage = "Invalid request method: " + ex.getMethod();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(System.currentTimeMillis())
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .title(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())
                .message(errorMessage)
                .build();

        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .headers(responseHeaders)
                .body(errorResponse);
    }

    @ExceptionHandler(RecordAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleRecordAlreadyExistException(RecordAlreadyExistException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(System.currentTimeMillis())
                .status(HttpStatus.CONFLICT.value())
                .title(HttpStatus.CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .build();

        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .headers(responseHeaders)
                .body(errorResponse);
    }

}
