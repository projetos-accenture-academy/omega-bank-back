package com.gama.exceptions;

public class AccountDoesNotExistsException extends Exception {
	
	public AccountDoesNotExistsException() {
		super("A conta informada não existe.");
		
	}
}
