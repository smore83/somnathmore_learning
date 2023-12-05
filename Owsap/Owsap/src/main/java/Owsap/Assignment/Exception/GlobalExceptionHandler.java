package Owsap.Assignment.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UserException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse>  errorResponseResponseEntity() {
        ErrorResponse errorResponse = ErrorResponse.builder().message(HttpStatus.NOT_FOUND.getReasonPhrase()).
                build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
