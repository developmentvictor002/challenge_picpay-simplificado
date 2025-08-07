package br.com.victor.picpay_simplificado.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletResponseDto(
        UUID walletId,
        BigDecimal balance,
        UserSummaryDto holder
) {
}
