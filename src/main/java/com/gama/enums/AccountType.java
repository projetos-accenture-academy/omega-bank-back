package com.gama.enums;

/**
 * Enumerador dos tipos de contas dos usuários, sendo: <b>CB</b> - Conta Banco
 * <b>CC</b> - Conta Corrent
 * 
 * @author Kellison
 *
 */
public enum AccountType {
	CB("CONTA BANCO"), CC("CONTA CREDITO");

	private String description;

	private AccountType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}