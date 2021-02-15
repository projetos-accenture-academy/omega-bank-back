package com.gama.model.dto;

import java.util.List;

import com.gama.model.Lancamento;

public class AccountDTO {
	private Integer id;
	private Double balance;
	private String description;
	private Integer numberOfStatements;
	private List<Lancamento> accountStatements;
	
	public AccountDTO() {
		
	}

	public AccountDTO(Integer id, Double balance, String description, List<Lancamento> accountStatements, Integer numberOfStatements) {
		super();
		this.id = id;
		this.balance = balance;
		this.description = description;
		this.accountStatements = accountStatements;
		this.numberOfStatements = numberOfStatements;
	}
	
	
	
	public Integer getNumberOfStatements() {
		return numberOfStatements;
	}

	public void setNumberOfStatements(Integer numberOfStatements) {
		this.numberOfStatements = numberOfStatements;
	}


	public Double getBalance() {
		return balance;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public List<Lancamento> getAccountStatements() {
		return accountStatements;
	}
	
	
	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
