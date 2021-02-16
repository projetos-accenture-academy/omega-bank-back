package com.gama.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gama.model.Usuario;
import com.gama.repository.UsuarioRepository;






@RestController
@RequestMapping(path = "/usuario")
public class UserController  {

	@Autowired
	private UsuarioRepository service;	
	
	
	
	
	@PostMapping()
	public void post(@RequestBody Usuario usuario){
		this.service.save(usuario);
	}
	
	@PutMapping()
	public void put(@RequestBody Usuario usuario){
		
		this.service.save(usuario);
	}
	
	@GetMapping(path = "/{usuario}") //@PathVariable
	public Optional<Usuario> listar(@PathVariable String usuario){
		System.out.println("Buscando usuario " + usuario);
		return service.findByLogin(usuario);
	}
	
	
	
	
	
	
}
