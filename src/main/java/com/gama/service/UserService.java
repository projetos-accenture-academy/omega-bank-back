package com.gama.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gama.model.User;
import com.gama.repository.UserRepository;
import com.gama.utils.UserValidator;

@Component
public class UserService {

	@Autowired
	private UserRepository usuarioRepository;
	
	private UserValidator userValidator;

	public User salvarUsuario(User user) throws Exception {
		userValidator = new UserValidator(user);
		if (usuarioRepository.existsById(user.getId())) {
			throw new Exception("Usuário já cadastrado!");
		} else if (!userValidator.valid()) {
			throw new Exception(
					String.format("Falha ao inserir usuário: {0}{1}", System.lineSeparator(), userValidator.getListError()));
		} else {
			return usuarioRepository.save(user);
		}
	}
	
	public User alterarUsuario(User user) throws Exception {
		userValidator = new UserValidator(user);
		if (!usuarioRepository.existsById(user.getId())) {
			throw new Exception("Usuário não cadastrado!");
		} else if (!userValidator.valid()) {
			throw new Exception(
					String.format("Falha ao alterar usuário: {0}{1}", System.lineSeparator(), userValidator.getListError()));
		} else {
			return usuarioRepository.save(user);
		}
	}
	
	public void deletarUsuario(User user) {
		usuarioRepository.delete(user);
	}

	public void deletarUsuarioById(int id) {
		usuarioRepository.deleteById(id);
	}

	public Optional<User> findUsuarioById(int id) {
		return usuarioRepository.findById(id);
	}

	public Optional<User> findUsuarioByLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}

	public Iterable<User> FindAllUsuarios() {
		return usuarioRepository.findAll();
	}

}
