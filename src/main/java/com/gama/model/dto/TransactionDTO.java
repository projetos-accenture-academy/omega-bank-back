package com.gama.model.dto;

import java.time.LocalDate;

import com.gama.enums.TransactionType;
import com.gama.model.Account;
import com.gama.model.AccountPlan;
import com.gama.model.Transaction;

public class TransactionDTO {
	private Long id;
	private TransactionType transactionType;
	private AccountPlan accountPlan;
	private Account sourceAccount;
	private Account destinationAccount;
	private LocalDate date;
	private Double value;
	private String description;
	
	
	public TransactionDTO() {}


	public TransactionDTO(Long id, TransactionType transactionType, AccountPlan accountPlan, Account sourceAccount, 
			Account destinationAccount, LocalDate date, Double value, String description) 
	{
		this.id = id;
		this.accountPlan = accountPlan;
		this.sourceAccount = sourceAccount;
		this.destinationAccount = destinationAccount;
		this.date = date;
		this.value = value;
		this.description = description;
	}
	
	public static TransactionDTO transformToDTO(Transaction t) throws IllegalArgumentException, Exception
	{
		//get the transaction type based on which accounts are null
		if(t.getDestinationAccount()==null && t.getSourceAccount()==null)
		{
			throw new IllegalArgumentException("Error: Transaction cannot have source and destination accounts as null");
		}
		else
		{
			TransactionType tt;
			if(t.getDestinationAccount()==null)//money is leaving account, it's a Debit
			{
				tt= TransactionType.D;
			}
			else if(t.getSourceAccount()==null)//money is entering account, it's a receipt
			{
				tt = TransactionType.R;
			}
			else//both are not null, it's a transfer
			{
				tt= TransactionType.T;
			}
			
			TransactionDTO newT = new TransactionDTO(t.getId(), tt, t.getAccountPlan(), t.getSourceAccount(), 
									t.getDestinationAccount(), t.getDate(), t.getValue(), t.getDescription());
			return newT;
			
		}
		
		
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


	public TransactionType getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	
	
}
