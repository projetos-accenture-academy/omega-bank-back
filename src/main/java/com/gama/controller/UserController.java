package com.gama.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gama.model.User;
import com.gama.model.dto.UserDTO;
import com.gama.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserService usuarioService;

	@Autowired
	private ModelMapper modelMapper = new ModelMapper();
	
	@CrossOrigin
	@PostMapping
	public Object incluir(@RequestBody UserDTO userDTO) {
		try {
			return new ResponseEntity<User>(usuarioService.salvarUsuario(modelMapper.map(userDTO, User.class)), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin
	@PutMapping
	public Object alterar(@RequestBody UserDTO userDTO) {
		try {
			return new ResponseEntity<User>(usuarioService.alterarUsuario(modelMapper.map(userDTO, User.class)), HttpStatus.OK);
		} catch (Exception e) {			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
//
//	@CrossOrigin
//	@GetMapping
//	@ResponseStatus(value = HttpStatus.OK)
//	public List<User> listarTodos() {
//		return usuarioService.FindAllUsuarios();
//	}
//	
//	@CrossOrigin
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<Optional<User>> buscarPorId(@RequestParam int id) {
//		Optional<User> user = usuarioService.findUsuarioById(id);
//		if (user.isPresent()) {
//			return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//	
//	@CrossOrigin
//	@GetMapping(value = "/{login}")
//	public ResponseEntity<Optional<User>> buscarPorLogin(@RequestParam String login) {
//		Optional<User> user = usuarioService.findUsuarioByLogin(login);
//		if (user.isPresent()) {
//			return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}		
//	}
//	
//	@CrossOrigin
//	@DeleteMapping(value = "/{user}") 
//	@ResponseStatus(value = HttpStatus.OK)
//	public void excluir(@RequestParam User user) {
//		usuarioService.deletarUsuario(user);
//	}
//
//	@CrossOrigin
//	@DeleteMapping(value = "/{id}") 
//	@ResponseStatus(value = HttpStatus.OK)
//	public void excluirPorId(@RequestParam int id) {
//		usuarioService.deletarUsuarioById(id);
//	}

}
