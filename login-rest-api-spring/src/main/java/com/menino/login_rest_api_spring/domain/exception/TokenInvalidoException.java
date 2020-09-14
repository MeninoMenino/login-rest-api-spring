package com.menino.login_rest_api_spring.domain.exception;

public class TokenInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TokenInvalidoException(){
		super("Token inválido");
	}
}
