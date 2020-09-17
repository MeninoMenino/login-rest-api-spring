package com.menino.login_rest_api_spring.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.menino.login_rest_api_spring.domain.exception.EmailJaCadastradoException;
import com.menino.login_rest_api_spring.domain.exception.TokenInvalidoException;
import com.menino.login_rest_api_spring.domain.model.Usuario;
import com.menino.login_rest_api_spring.domain.repository.UsuarioRepository;
import com.menino.login_rest_api_spring.domain.service.AutenticacaoService;
import com.menino.login_rest_api_spring.domain.service.TokenService;
import com.menino.login_rest_api_spring.domain.service.UsuarioCadastroService;

//Classe controladora de usuários, mapeando o caminho /usuarios
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	UsuarioCadastroService usuarioCadastroService;

	@Autowired
	TokenService tokenService;

	@Autowired
	AutenticacaoService autenticacaoService;

	//Lista todos os usuários
	//Precisa de um token válido
	@GetMapping
	public List<Usuario> listar(@RequestHeader String Authorization){
		if (autenticacaoService.validarToken(Authorization)) {
			return usuarioRepository.findAll();
		} else {
			throw new TokenInvalidoException();
		}
	}

	//Busca usuário por email
	//Precisa de um token válido
	@GetMapping("/{email}")
	public ResponseEntity<Usuario> buscar(@PathVariable String email, 
										  @RequestHeader String Authorization){
		if (autenticacaoService.validarToken(Authorization)) {
			Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

			/*Caso não encontre o usuário, retorna status 404
   		  Caso encontre o usuário, retorna status 200 e o registro */
			if(!usuario.isPresent()) {
				return ResponseEntity.notFound().build();	
			} else {
				return ResponseEntity.ok(usuario.get());
			}
		} else {
			throw new TokenInvalidoException();
		}
	}

	//Insere um novo usuário
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario inserir(@Valid @RequestBody Usuario usuario){
		Optional<Usuario> emailExistente = usuarioRepository.findByEmail(usuario.getEmail());
		if(emailExistente.isPresent() && usuario.getEmail().equals(emailExistente.get().getEmail())) {
			throw new EmailJaCadastradoException();
		} else {
			return usuarioRepository.save(usuario);
		}
	}

	//Altera os dados de um usuário existente
	//Precisa de um token válido
	@PutMapping("/{email}")
	public ResponseEntity<Usuario> alterar(@Valid @RequestBody Usuario usuario, 
			@PathVariable String email,
			@RequestHeader String Authorization){

		if (autenticacaoService.validarToken(Authorization)) {
			//Confere se o id informado existe
			Optional<Usuario> usuarioBanco = usuarioRepository.findByEmail(email);
			if(!usuarioBanco.isPresent()) {
				return ResponseEntity.notFound().build();
			} else {
				usuario.setId(usuarioBanco.get().getId());
				return ResponseEntity.ok(usuarioCadastroService.verificarEmailAlterado(usuario));
			}
		} else {
			throw new TokenInvalidoException();
		}
	}

	//Deleta o registro do usuário
	//Precisa de um token válido
	@DeleteMapping("/{email}")
	public ResponseEntity<Void> deletar(@PathVariable String email,
										@RequestHeader String Authorization) {
		if (autenticacaoService.validarToken(Authorization)) {
			Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
			if(!usuario.isPresent()) {
				return ResponseEntity.notFound().build();
			} else {
				usuarioRepository.deleteById(usuario.get().getId());
				return ResponseEntity.noContent().build();
			}
		} else {
			throw new TokenInvalidoException();
		}
	}
}
