package com.gama.model.dto;

import java.util.List;

import com.gama.enums.AccountType;

public class AccountDTO {
	
	private int id;
	private AccountType tipo;
	private String numero;
	private double saldo;
	private String descricao;
	private int usuario_id;
	private List<TransactionDTO> transactions;
	
	public AccountDTO() { }
	
	

	public AccountDTO(int id, AccountType tipo, String numero, double saldo, String descricao, int usuario_id,
			List<TransactionDTO> transactions) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.numero = numero;
		this.saldo = saldo;
		this.descricao = descricao;
		this.usuario_id = usuario_id;
		this.transactions = transactions;
	}



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



	public List<TransactionDTO> getTransactions() {
		return transactions;
	}


	
	
}
