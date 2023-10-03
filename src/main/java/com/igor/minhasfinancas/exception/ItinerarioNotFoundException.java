package com.igor.minhasfinancas.exception;

public class ItinerarioNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ItinerarioNotFoundException(String mensagem) {
        super(mensagem);
    }

    public ItinerarioNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

}
