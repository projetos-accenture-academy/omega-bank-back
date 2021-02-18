package com.gama.exceptions;

public class BalanceNotEnoughException extends Exception {

	public BalanceNotEnoughException(Double valor) {
		super("O saldo atual é insuficiente para realizar uma operação de débito/transferência no valor de " + valor);		
	}
}
