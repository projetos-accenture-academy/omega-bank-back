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
	
	public static String getAccountTypeString(AccountType at)
	{
		if(at==AccountType.CC)
		{
			return "CC";
		}
		else if(at==AccountType.CB)
		{
			return "CB";
		}
		else
			throw new IllegalArgumentException();
	}
}