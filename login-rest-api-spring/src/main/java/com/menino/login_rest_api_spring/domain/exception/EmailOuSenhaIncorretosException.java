package com.menino.login_rest_api_spring.domain.exception;

//Classe de erro no login
public class EmailOuSenhaIncorretosException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public EmailOuSenhaIncorretosException(){
		super("E-mail ou senha incorretos");
	}
}
