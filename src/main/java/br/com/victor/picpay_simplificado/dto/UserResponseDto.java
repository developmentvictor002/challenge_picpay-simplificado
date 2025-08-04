package br.com.victor.picpay_simplificado.dto;

import br.com.victor.picpay_simplificado.entity.User;

import java.util.UUID;

public record UserResponseDto(
        UUID userId,
        String fullName,
        String email
) {

    public static UserResponseDto fromUser(User user) {
        return new UserResponseDto(
                user.getUserId(),
                user.getFullName(),
                user.getEmail()
        );
    }
}
