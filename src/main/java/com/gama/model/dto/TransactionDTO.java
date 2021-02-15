package com.gama.model.dto;

import java.time.LocalDate;

import com.gama.model.Account;
import com.gama.model.AccountPlan;

public class TransactionDTO {
	private Long id;
	private AccountPlan accountPlan;
	private Account sourceAccount;
	private Account destinationAccount;
	private LocalDate date;
	private Double value;
	private String description;
	
	
	public TransactionDTO() {}


	public TransactionDTO(Long id, AccountPlan accountPlan, Account sourceAccount, Account destinationAccount,
			LocalDate date, Double value, String description) 
	{
		this.id = id;
		this.accountPlan = accountPlan;
		this.sourceAccount = sourceAccount;
		this.destinationAccount = destinationAccount;
		this.date = date;
		this.value = value;
		this.description = description;
	}


	public Long getId() {
		return id;
	}


	public AccountPlan getAccountPlan() {
		return accountPlan;
	}


	public void setAccountPlan(AccountPlan accountPlan) {
		this.accountPlan = accountPlan;
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
	
	
}
