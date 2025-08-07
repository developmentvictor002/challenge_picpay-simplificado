package br.com.victor.picpay_simplificado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferRequestDto(
        @NotNull(message = "Sender wallet ID is required")
        UUID senderWalletId,

        @NotNull(message = "Receiver wallet ID is required")
        UUID receiverWalletId,

        @NotNull(message = "Transfer value is required")
        @Positive(message = "Transfer value must be positive")
        BigDecimal value
) {
}
