package com.gama.utils;

import javax.persistence.Entity;

//Used by TransactionRepo and Service classes to get the sum of values from an account, categorized by account plan


public class CategorizedTransactionAuxiliary {

	private Double value;
	
	private String description;
	
	public Double getValueSum() {
		return value;
	}
	public void setValueSum(Double valueSum) {
		this.value = valueSum;
	}
	public String getTransactionTypeDescription() {
		return description;
	}
	public void setTransactionTypeDescription(String transactionTypeDescription) {
		this.description = transactionTypeDescription;
	}
	
	public CategorizedTransactionAuxiliary() {}
	
	public CategorizedTransactionAuxiliary(Double valueSum, String transactionTypeDescription) {
		
		this.value = valueSum;
		this.description = transactionTypeDescription;
	}
	
	
	
}
