package com.gama.utils;

//Wrapper class for Sum Categorization Request input
public class AccountCategoriesSum {
	public Long accountId;
	public String startDate;
	public String endDate;
	public AccountCategoriesSum() {}
	public AccountCategoriesSum(Long accountId, String startDate, String endDate) {
		super();
		this.accountId = accountId;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
}
