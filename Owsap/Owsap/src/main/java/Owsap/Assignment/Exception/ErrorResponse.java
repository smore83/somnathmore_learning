package Owsap.Assignment.Exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
    private String message;
    private int status;
    private String title;
}
