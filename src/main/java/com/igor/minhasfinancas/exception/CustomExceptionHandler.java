package com.igor.minhasfinancas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.igor.minhasfinancas.model.entity.ErrorResponse;

public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	 @ExceptionHandler(TipoViagemNotFoundException.class)
	    public ResponseEntity<Object> handleTipoViagemNotFoundException(
	            TipoViagemNotFoundException ex, WebRequest request) {
	        // Você pode personalizar a resposta de erro aqui, por exemplo, definindo um status HTTP específico
	        // e adicionando detalhes adicionais, se necessário
	        ErrorResponse errorResponse = new ErrorResponse("Tipo de Viagem não encontrado", ex.getMessage());
	        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	    }
	 
}
