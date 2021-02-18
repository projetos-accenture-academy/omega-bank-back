package com.gama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.model.User;

/**
 * Métodos de usuário
 * @author Alessandra
 *
 */

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByLogin(String login);
	
	boolean existsByLogin(String login);
	
}