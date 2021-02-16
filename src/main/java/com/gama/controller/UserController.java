package com.gama.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gama.model.Usuario;

import com.gama.service.UsuarioService;





@RestController
@RequestMapping(path = "/usuario")
public class UserController  {

	@Autowired
	
	private UsuarioService usuarioService;
	
	
	
	@PostMapping()
	public void post(@RequestBody Usuario user){
		try {
			usuarioService.salvarUsuario(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@PutMapping()
	public void put(@RequestBody Usuario usuario){
		
		try {
			usuarioService.alterarUsuario(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping(path = "/{usuario}") //@PathVariable
	public Optional<Usuario> listar(@PathVariable String usuario){
		System.out.println("Buscando usuario " + usuario);
		return usuarioService.findUsuarioByLogin(usuario);
	}
	
	@DeleteMapping(path = "/{user}") //@PathVariable
	public void delete(@PathVariable Integer user) {
		
			usuarioService.deletarUsuarioById(user);
		
	}
	
	
	
	
}
