package com.menino.login_rest_api_spring.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menino.login_rest_api_spring.domain.exception.TokenExpiradoException;
import com.menino.login_rest_api_spring.domain.exception.TokenInvalidoException;
import com.menino.login_rest_api_spring.domain.repository.UsuarioRepository;

import io.jsonwebtoken.Claims;

@Service
public class AutenticacaoService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TokenService tokenService;
	
	public boolean validarToken(String token) {
		
		try {
			String tokenTratado = token.replace("Bearer ", "");
			Claims claims = tokenService.decodificarToken(tokenTratado);
			
			//Verifica se o token está expirado
			if(claims.getExpiration().before(new Date(System.currentTimeMillis()))) {
				throw new TokenExpiradoException();
			} else {
				return true;
			}
		} catch (TokenExpiradoException tokenExpiradoException) {
			tokenExpiradoException.printStackTrace();
			throw tokenExpiradoException;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TokenInvalidoException();
		}
		
	}
}