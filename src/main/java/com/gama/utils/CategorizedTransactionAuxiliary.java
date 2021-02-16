package com.gama.utils;

//Used by TransactionRepo and Service classes to get the sum of values from an account, categorized by account plan
public class CategorizedTransactionAuxiliary {

	private Double valueSum;
	private String transactionTypeDescription;
	
	public Double getValueSum() {
		return valueSum;
	}
	public void setValueSum(Double valueSum) {
		this.valueSum = valueSum;
	}
	public String getTransactionTypeDescription() {
		return transactionTypeDescription;
	}
	public void setTransactionTypeDescription(String transactionTypeDescription) {
		this.transactionTypeDescription = transactionTypeDescription;
	}
	
	public CategorizedTransactionAuxiliary(Double valueSum, String transactionTypeDescription) {
		
		this.valueSum = valueSum;
		this.transactionTypeDescription = transactionTypeDescription;
	}
	
	
	
}
