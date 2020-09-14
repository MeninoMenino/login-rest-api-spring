package com.menino.login_rest_api_spring.domain.exception;

public class TokenExpiradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TokenExpiradoException() {
		super("Token de acesso expirado");
	}
	
}
