package br.com.victor.picpay_simplificado.exception;

public class MerchantCannotTransferException extends RuntimeException {
    public MerchantCannotTransferException(String message) {
        super(message);
    }
}
