package br.com.victor.picpay_simplificado.dto;

import java.util.UUID;

public record WalletRequestDto(
        UUID userId
) {
}
