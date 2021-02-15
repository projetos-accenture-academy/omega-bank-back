package com.gama.model.dto;

import java.util.List;

public class DashboardDTO {
	private List<AccountDTO> accounts;
	private Integer nuberOfAccounts;
	
	public DashboardDTO() {
		
	}
	
	public DashboardDTO(List<AccountDTO> accounts, Integer nuberOfAccounts) {
		super();
		this.accounts = accounts;
		this.nuberOfAccounts = nuberOfAccounts;
	}

	public List<AccountDTO> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDTO> accounts) {
		this.accounts = accounts;
	}

	public Integer getNuberOfAccounts() {
		return nuberOfAccounts;
	}

	public void setNuberOfAccounts(Integer nuberOfAccounts) {
		this.nuberOfAccounts = nuberOfAccounts;
	}
	
	
	
	
}
