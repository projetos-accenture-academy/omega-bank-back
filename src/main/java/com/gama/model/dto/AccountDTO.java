package com.gama.model.dto;

import java.util.List;

import com.gama.model.Transaction;

public class AccountDTO {
	
	private int id;
	private TipoConta tipo;
	private String numero;
	private double saldo;
	private String descricao;
	private int usuario_id;
	
	public AccountDTO() { }

	public int getId() {
		return id;
	}
	
	public TipoConta getTipo() {
		return tipo;
	}

	public String getNumero() {
		return numero;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public double getDescricao() {
		return descricao;
	}
	
	public id getUsuarioId() {
		return usuario_id;
	}	
	
}
