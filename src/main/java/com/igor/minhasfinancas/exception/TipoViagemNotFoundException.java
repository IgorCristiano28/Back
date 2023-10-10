package com.igor.minhasfinancas.exception;

import java.util.UUID;

public class TipoViagemNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TipoViagemNotFoundException(UUID tipoViagemId) {
	        super("Tipo de viagem não encontrado para o ID: " + tipoViagemId);
	    }

	public TipoViagemNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoViagemNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public TipoViagemNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TipoViagemNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TipoViagemNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
