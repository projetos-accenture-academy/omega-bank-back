package com.gama.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Tabela de Lançamentos
 * @author Brian
 *
 */
@Entity
@Table(name = "lancamentos")
public class Lancamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//TODO: Inserir FK PlanoConta

	//Is null when it's a deposit
	@JoinColumn (name = "id_conta_origem", nullable = true)
	@ManyToOne
	private Account contaOrigem;
	
  //It's null when it's a withdrawal
	@JoinColumn(name = "id_conta_destino", nullable = true)
	@ManyToOne
	private Account contaDestino;
	
	@Column
	private LocalDate data;
	
	@Column
	private Double valor;
	

	@Column
	private String descricao;

	
	
	
	public Lancamento()
	{
		
	}

	public Long getId() {
		return id;
	}

	public Account getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Account contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Account getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Account contaDestino) {
		this.contaDestino = contaDestino;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
