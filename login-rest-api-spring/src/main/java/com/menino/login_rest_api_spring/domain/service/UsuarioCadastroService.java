package com.menino.login_rest_api_spring.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menino.login_rest_api_spring.domain.exception.EmailJaCadastradoException;
import com.menino.login_rest_api_spring.domain.model.Usuario;
import com.menino.login_rest_api_spring.domain.repository.UsuarioRepository;

@Service
public class UsuarioCadastroService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	//Valida se o email informado no PUT já está cadastrado em outro registro
	public Usuario verificarEmailAlterado(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		if(usuarioExistente.isPresent() && usuarioExistente.get().getId() != usuario.getId()) {
			throw new EmailJaCadastradoException();
		} else {
			return salvar(usuario);
		}
	}
	
	//Verifica se o email informado já está cadastrado
	public boolean verificarEmailExistente(String email) {
		if(usuarioRepository.findByEmail(email).isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	
	//Caso passe pela validação, insere os dados no banco
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
}
