package com.gama.utils;

import java.util.ArrayList;
import java.util.List;

import com.gama.model.User;

public class UserValidator {

	private User user;
	
	private final List<String> listError = new ArrayList<String>();

	public  List<String> getListError() {
		return listError;
	}
	
	public UserValidator(User user) {
		this.user = user;
	}
	
	public boolean valid() {
		if (Validator.isEmptyValue(user.getLogin())) {
			listError.add("O login do usuário deve ser informado.");
			return false;
		}		
		if (user.getLogin().length() > 20) {
			listError.add("O login do usuário deve conter no máximo 20 caracteres.");
			return false;
		}
		
		if (Validator.isEmptyValue(user.getSenha())) {
			listError.add("A senha do usuário deve ser informada.");
			return false;
		}		
		if (user.getSenha().length() < 6) {
			listError.add("A senha do usuário deve conter no mínimo 6 caracteres.");
			return false;
		}
		if (user.getSenha().length() > 8) {
			listError.add("A senha do usuário deve conter no máximo 8 caracteres.");
			return false;
		}	
		
		if (Validator.isEmptyValue(user.getNome())) {
			listError.add("O nome do usuário deve ser informado.");
			return false;
		}		
	    if (user.getNome().length() > 30) {
			listError.add("O nome do usuário deve conter no máximo 30 caracteres.");
			return false;
		}
	    
	    if (Validator.isEmptyValue(user.getCpf())) {
			listError.add("O cpf deve ser informado.");
			return false;
		}	    
	 		
		return true;
	}	
	
}
