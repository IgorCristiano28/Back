package com.igor.backend.exception;

public class ViagemNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ViagemNotFoundException(String mensagem) {
        super(mensagem);
    }

    public ViagemNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

}
