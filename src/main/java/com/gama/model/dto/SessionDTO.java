package com.gama.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.gama.model.Account;
import com.gama.model.AccountPlan;
import com.gama.model.User;


public class SessionDTO {
	private String token;
	private LocalDateTime tokenExpirationTime;
	private LocalDateTime tokenExpeditionTime;
	private User user;
	private List<Account> accounts;
	private List<AccountPlan> plans;
	
	public SessionDTO() {
		
	}
	
	public SessionDTO(User user, String token, LocalDateTime tokenExpirationTime, LocalDateTime tokenExpeditionTime, List<Account> accounts, List<AccountPlan> plans ) {
		super();
		this.user = user;
		this.token = token;
		this.tokenExpirationTime = tokenExpirationTime;
		this.tokenExpeditionTime = tokenExpeditionTime;
		this.accounts = accounts;
		this.plans = plans;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getTokenExpirationTime() {
		return tokenExpirationTime;
	}

	public void setTokenExpirationTime(LocalDateTime tokenExpirationTime) {
		this.tokenExpirationTime = tokenExpirationTime;
	}
	
	public LocalDateTime getTokenExpeditionTime() {
		return tokenExpeditionTime;
	}

	public void setTokenExpeditionTime(LocalDateTime tokenExpeditionTime) {
		this.tokenExpeditionTime = tokenExpeditionTime;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<AccountPlan> getPlans() {
		return plans;
	}

	public void setPlans(List<AccountPlan> plans) {
		this.plans = plans;
	}
	
	

}
