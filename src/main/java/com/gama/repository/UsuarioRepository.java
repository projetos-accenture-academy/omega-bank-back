package com.gama.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.gama.model.User;

/**
 * Métodos de usuário
 * @author Alessandra
 *
 */

public interface UsuarioRepository extends CrudRepository<User, Integer> {
	
	Optional<User> findByLogin(String login);
	
}