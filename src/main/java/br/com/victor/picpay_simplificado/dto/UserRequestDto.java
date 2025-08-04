package br.com.victor.picpay_simplificado.dto;

import br.com.victor.picpay_simplificado.entity.User;
import br.com.victor.picpay_simplificado.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank(message = "The full name field is required.")
        @Size(min = 3, max = 100, message = "The full name must be between 3 and 100 characters.")
        String fullName,

        @NotBlank(message = "The CPF field is required.")
        String cpf,

        @NotBlank(message = "The email field is required.")
        @Size(max = 100, message = "The email must be at most 100 characters.")
        @Email(message = "Invalid email format.")
        String email,

        @NotBlank(message = "The password field is required.")
        @Size(min = 6, message = "The password must be at least 6 characters long")
        String password,

        @NotBlank
        UserType type
) {
    public User toUser() {
        return new User(
                fullName,
                cpf,
                email,
                password,
                type
        );
    }
}
