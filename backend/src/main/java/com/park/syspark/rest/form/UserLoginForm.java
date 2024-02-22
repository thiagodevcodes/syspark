package com.park.syspark.rest.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginForm {
    @NotNull(message = "The email field cannot be empty")
    @NotBlank(message = "The email field cannot be blank.")
    @Size(min = 3, max = 255, message = "The email address must be between 3 and 255 characters.")
    @Email(message = "The email must be a valid email address.")
    private String email;

    @NotNull(message = "The password field cannot be empty")
    @NotBlank(message = "The password field cannot be blank.")
    @Size(min = 8, max = 255, message = "The password must have at least 8 characters.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "The password must contain at least one lowercase letter, one uppercase letter, one digit, and one special symbol.")
    private String password;
}