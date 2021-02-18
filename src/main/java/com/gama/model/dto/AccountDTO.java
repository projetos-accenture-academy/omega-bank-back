package com.gama.model.dto;

import com.gama.enums.AccountType;

public class AccountDTO {
	
	private int id;
	private AccountType tipo;
	private String numero;
	private double saldo;
	private String descricao;
	private int usuario_id;
	
	public AccountDTO() { }

	public int getId() {
		return id;
	}
	
	public AccountType getTipo() {
		return tipo;
	}

	public String getNumero() {
		return numero;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public int getUsuarioId() {
		return usuario_id;
	}	
	
}
