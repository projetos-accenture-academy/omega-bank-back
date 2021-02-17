package com.gama.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gama.model.User;
import com.gama.model.dto.UserDTO;
import com.gama.service.UserService;

@Controller
@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserService usuarioService;

	@Autowired
	private ModelMapper modelMapper = new ModelMapper();
	
	@PostMapping(produces="application/json", consumes="application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Object incluir(@RequestBody UserDTO userDTO) {
		try {
			return usuarioService.salvarUsuario(modelMapper.map(userDTO, User.class));
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@PutMapping(produces="application/json", consumes="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Object alterar(@RequestBody UserDTO userDTO) {
		try {
			return usuarioService.alterarUsuario(modelMapper.map(userDTO, User.class));
		} catch (Exception e) {			
			return e.getMessage();
		}
	}

	@GetMapping(produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public List<User> listarTodos() {
		return usuarioService.FindAllUsuarios();
	}
	
	@GetMapping(path = "/{id}", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Optional<User> buscarPorId(int id) {
		return usuarioService.findUsuarioById(id);
	}
	
	@GetMapping(path = "/{login}", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Optional<User> buscarPorLogin(String login) {
		return usuarioService.findUsuarioByLogin(login);
	}
	
	@DeleteMapping(path = "/{user}") 
	@ResponseStatus(value = HttpStatus.OK)
	public void excluir(User user) {
		usuarioService.deletarUsuario(user);
	}

	@DeleteMapping(path = "/{id}") 
	@ResponseStatus(value = HttpStatus.OK)
	public void excluirPorId(Integer id) {
		usuarioService.deletarUsuarioById(id);
	}

}
