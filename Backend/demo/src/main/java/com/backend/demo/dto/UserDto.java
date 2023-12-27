package com.backend.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    @Size(max = 50, message = "Email should be at most 50 characters long.")
    @Email
    private String email;
    @Size(max = 30, message = "Username should be at most 30 characters long.")
    private String userName;
    @NotNull(message = "password should not be null")
    private String password;
    @Size(max = 30, message = "Last name should be at most 30 characters long.")
    private String lastName;
    private List<PostDto> postList;



}
