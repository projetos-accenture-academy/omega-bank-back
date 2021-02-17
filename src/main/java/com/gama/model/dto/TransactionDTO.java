package com.gama.model.dto;

import java.time.LocalDate;

import com.gama.enums.TransactionType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TransactionDTO {
	
	@ApiModelProperty(value = "Tipo", required = true)
	private TransactionType transactionType;
	
	@ApiModelProperty(value = "Plano de conta", required = true)
	private int accountPlan;
	
	@ApiModelProperty(value = "Conta de origem")
	private int sourceAccount;
	
	@ApiModelProperty(value = "Conta de destino")
	private int destinationAccount;
	
	@ApiModelProperty(value = "Data", example = "01/01/2021", required = true)
	private LocalDate date;
	
	@ApiModelProperty(value = "Valor", example = "0,00", required = true)
	private Double value;
	
	@ApiModelProperty(value = "Descrição", required = true)
	private String description;		
	
	public int getAccountPlan() {
		return accountPlan;
	}

	public void setAccountPlan(int accountPlan) {
		this.accountPlan = accountPlan;
	}

	public int getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(int sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public int getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(int destinationAccount) {
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
