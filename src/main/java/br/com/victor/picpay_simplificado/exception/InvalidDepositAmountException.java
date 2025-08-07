package br.com.victor.picpay_simplificado.exception;

public class InvalidDepositAmountException extends RuntimeException {
    public InvalidDepositAmountException(String message) {
        super(message);
    }
}
