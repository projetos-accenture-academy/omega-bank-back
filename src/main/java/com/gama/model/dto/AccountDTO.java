package com.gama.model.dto;

import com.gama.enums.TipoConta;

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
	
	public String getDescricao() {
		return descricao;
	}
	
	public int getUsuarioId() {
		return usuario_id;
	}	
	
}
