package com.igor.minhasfinancas.exception;

public class ChecklistNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ChecklistNotFoundException(String mensagem) {
        super();
    }

    public ChecklistNotFoundException(String mensagem, Throwable causa) {
        super();
    }

}
