package com.gama.model.dto.response;

import java.time.LocalDate;

import com.gama.enums.TransactionType;
import com.gama.model.Account;
import com.gama.model.AccountPlan;

public class TransactionDTOResponse {

	private Long id;
	private TransactionType transactionType;
	private AccountPlan accountPlan;
	private Account sourceAccount;
	private Account destinationAccount;
	private LocalDate date;
	private Double value;
	private String description;
	
	public Long getId() {
		return id;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public AccountPlan getAccountPlan() {
		return accountPlan;
	}
	public Account getSourceAccount() {
		return sourceAccount;
	}
	public Account getDestinationAccount() {
		return destinationAccount;
	}
	public LocalDate getDate() {
		return date;
	}
	public Double getValue() {
		return value;
	}
	public String getDescription() {
		return description;
	}
	
}
