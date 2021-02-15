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

import com.sun.istack.NotNull;

/**
 * Tabela de Lançamentos
 * @author Brian
 *
 */
@Entity
@Table(name = "lancamentos")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Never null
	
	@NotNull
	@JoinColumn(name = "id_plano_conta")
	private AccountPlan accountPlan;
	
	//Is null when it's a deposit
	@JoinColumn (name = "id_conta_origem", nullable = true)
	@ManyToOne
	private Account sourceAccount;
	
	//It's null when it's a withdrawal
	@JoinColumn(name = "id_conta_destino", nullable = true)
	@ManyToOne
	private Account destinationAccount;
	
	@Column
	@NotNull
	private LocalDate date;

	@Column
	@NotNull
	private Double value;
	
	@Column
	private String description;

	
	
	
	public Transaction()
	{
		
	}

	public Long getId() {
		return id;
	}

	public Account getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(Account sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public Account getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(Account destinationAccount) {
		this.destinationAccount = destinationAccount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public AccountPlan getAccountPlan() {
		return accountPlan;
	}

	public void setAccountPlan(AccountPlan accountPlan) {
		this.accountPlan = accountPlan;
	}

}