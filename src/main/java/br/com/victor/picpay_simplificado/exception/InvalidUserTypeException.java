package br.com.victor.picpay_simplificado.exception;

public class InvalidUserTypeException extends RuntimeException {
    public InvalidUserTypeException(String value) {
        super("Invalid user type: " + value);
    }
}
