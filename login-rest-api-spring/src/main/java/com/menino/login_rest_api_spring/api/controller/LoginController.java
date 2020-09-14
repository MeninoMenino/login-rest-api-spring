package com.menino.login_rest_api_spring.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.menino.login_rest_api_spring.api.dto.LoginDto;
import com.menino.login_rest_api_spring.api.dto.UsuarioAutenticadoDto;
import com.menino.login_rest_api_spring.domain.service.AutenticacaoService;
import com.menino.login_rest_api_spring.domain.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	AutenticacaoService autenticacaoService;
	
	@Autowired
	LoginService loginService;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public UsuarioAutenticadoDto efetuarLogin(@RequestBody @Valid LoginDto login){
		UsuarioAutenticadoDto usuarioAutenticado = loginService.autenticarLogin(login);
		return usuarioAutenticado;
	}
}
