package br.com.victor.picpay_simplificado.dto;

import java.math.BigDecimal;

public record DepositRequestDto(
        BigDecimal amount
) {
}
