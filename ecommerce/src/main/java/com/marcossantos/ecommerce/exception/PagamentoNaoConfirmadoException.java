package com.marcossantos.ecommerce.exception;

public class PagamentoNaoConfirmadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PagamentoNaoConfirmadoException(String msg) {
		super(msg);
	}

}
