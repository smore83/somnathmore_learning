package com.backend.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.backend.user.utils.Constants.*;

@Data
public class PostDto {
    private Long id;
    @NotEmpty(message = TITLE_VALIDATION)
    @Size(min = 4,message = TITLE_VALIDATION_STRENGTH)
    private String title;
    @Size(min = 4,message = DESCRIPTION_VALIDATION_STRENGTH)
    @NotEmpty(message = DESCRIPTION_VALIDATION)
    private String description;
}
