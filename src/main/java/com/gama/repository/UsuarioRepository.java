package com.gama.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.gama.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	
	Optional<Usuario> findByLogin(String login);
	

	
}