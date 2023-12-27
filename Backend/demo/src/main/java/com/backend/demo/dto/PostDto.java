package com.backend.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
    private Long id;
    @NotBlank
    @Size(max = 100, message = "Title should be at most 100 characters long.")
    private String title;
    @NotBlank
    @Size(max = 500, message = "Description should be at most 500 characters long.")
    private String description;

}
