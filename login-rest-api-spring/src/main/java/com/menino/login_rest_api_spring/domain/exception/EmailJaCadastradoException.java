package com.menino.login_rest_api_spring.domain.exception;

//Classe de erro de atualização
public class EmailJaCadastradoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public EmailJaCadastradoException(){
		super("E-mail já cadastrado!");
	}
}
