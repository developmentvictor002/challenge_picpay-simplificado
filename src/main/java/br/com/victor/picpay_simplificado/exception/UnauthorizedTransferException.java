package br.com.victor.picpay_simplificado.exception;

public class UnauthorizedTransferException extends RuntimeException {
    public UnauthorizedTransferException(String message) {
        super(message);
    }
}
