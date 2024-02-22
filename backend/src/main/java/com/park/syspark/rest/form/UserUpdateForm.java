package com.park.syspark.rest.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateForm {
    @NotNull(message = "The email field cannot be empty")
    @NotBlank(message = "The email field cannot be blank.")
    @Size(min = 3, max = 255, message = "The email address must be between 3 and 255 characters.")
    @Email(message = "The email must be a valid email address.")
    private String email;

    @NotNull(message = "O campo descrição não pode estar vazio")
    @NotBlank(message = "O campo descrição não pode ficar em branco.")
    @Size(min = 3, max = 100, message = "A descrição do papel de usuário deve ter entre 3 e 100 caracteres.")
    private String description;
}