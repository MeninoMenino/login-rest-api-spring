package com.menino.login_rest_api_spring.domain.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.menino.login_rest_api_spring.domain.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//Configura a criação dos tokens de acesso
@Service
public class TokenService {
	
	private static final long tempoExpiracao = 1800000;
	private String chave = "Chave";
	
	public String gerarToken(Usuario usuario) {
		return Jwts.builder()
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setSubject(usuario.getEmail())
				.setExpiration(new Date(System.currentTimeMillis() + tempoExpiracao))
				.signWith(SignatureAlgorithm.HS256, chave)
				.compact();
	}
	
	public Claims decodificarToken(String token) {
		return Jwts.parser()
				.setSigningKey(chave)
				.parseClaimsJws(token)
				.getBody();
	}
}
