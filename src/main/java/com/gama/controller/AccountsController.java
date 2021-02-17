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

import com.gama.model.Account;

import com.gama.service.AccountService;




@RestController
@RequestMapping(path = "/account")
public class AccountsController {

	private AccountService service;
	
	@Autowired
	
	
	
	
	@PostMapping()
	public void post(@RequestBody Account account){
		try {
			service.saveAccount(account);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@PutMapping()
	public void put(@RequestBody Integer account){
		
		try {
			service.updateAccountBalance(account, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/*
   
	 Embaixo estão os que podem ser implementados, acima ainda não testes (rauL)

	*/
	
	@GetMapping(path = "/{account}") //@PathVariable
	public void put(@PathVariable String account){
// esses resources ainda estão com problema na estrutura
	}
	
	@DeleteMapping(path = "/{account}") //@PathVariable
	public void delete(@PathVariable Integer account) {
		
			// esses resources ainda estão com problema na estrutura
		
	}
	

}
