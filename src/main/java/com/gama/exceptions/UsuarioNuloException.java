package com.gama.exceptions;

public class UsuarioNuloException extends Exception {

	public UsuarioNuloException() {
		super("O usuário da conta não pode ser nulo.");
	}

}
