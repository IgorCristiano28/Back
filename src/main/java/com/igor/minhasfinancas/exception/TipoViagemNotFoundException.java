package com.igor.minhasfinancas.exception;

import java.util.UUID;

public class TipoViagemNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TipoViagemNotFoundException(UUID tipoViagemId) {
	        super("Tipo de viagem não encontrado para o ID: " + tipoViagemId);
	    }

}
