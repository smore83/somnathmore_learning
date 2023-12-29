package com.backend.user.dto;

import com.backend.user.entity.Post;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

import static com.backend.user.utils.Constants.*;

@Data
public class UserDto {
    private Long id;

    @Email
    @NotEmpty(message = EMAIL_VALIDATION)
    private String email;

    @Pattern(regexp = PASSWORD_REGEX ,message = PASSWORD_VALIDATION)
    private String password;
    @NotEmpty(message = FIRSTNAME_VALIDATION)
    @Size(min = 4, message = FIRSTNAME_VALIDATION_STRENGTH)
    private String firstName;

    @NotEmpty(message = LASTNAME_VALIDATION)
    @Size(min = 4, message = LASTNAME_VALIDATION_STRENGTH)
    private String lastName;

    private List<Post> postList;
}
