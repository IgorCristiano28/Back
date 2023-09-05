package com.igor.minhasfinancas.exception;

public class ViagemNotFoundException extends RuntimeException {
	
	public ViagemNotFoundException(String mensagem) {
        super(mensagem);
    }

    public ViagemNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

}
