package com.park.syspark.rest.form;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateForm {
    @NotNull(message = "The email field cannot be empty")
    @NotBlank(message = "The email field cannot be blank.")
    @Size(min = 3, max = 255, message = "The email address must be between 3 and 255 characters.")
    @Email(message = "The email must be a valid email address.")
    private String email;

    @Valid
    private Set<RoleForm> roles;
}