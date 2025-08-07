package br.com.victor.picpay_simplificado.dto;

import java.util.UUID;

public record UserSummaryDto(
        UUID userId,
        String name,
        String email
) {
}
