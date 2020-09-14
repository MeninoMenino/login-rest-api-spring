package com.menino.login_rest_api_spring.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.menino.login_rest_api_spring.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	public Optional <Usuario> findByEmail(String email);
	public boolean existsByEmail(String email);
}
