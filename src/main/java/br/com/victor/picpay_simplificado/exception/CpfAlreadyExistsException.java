package br.com.victor.picpay_simplificado.exception;

public class CpfAlreadyExistsException extends RuntimeException {
    public CpfAlreadyExistsException(String cpf) {
        super("CPF already in use: " + cpf);
    }
}
