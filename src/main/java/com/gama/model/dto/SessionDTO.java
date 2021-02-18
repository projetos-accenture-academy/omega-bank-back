package com.gama.model.dto;

import java.util.Date;
import java.util.List;

import com.gama.model.Account;
import com.gama.model.AccountPlan;
import com.gama.model.User;


public class SessionDTO {
	private String login;
	private String token;

	private Date tokenExpirationTime;
	private Date tokenExpeditionTime;
	
	private UserSessionDTO user;

	private List<Account> accounts;
	private List<AccountPlan> plans;
	
	public SessionDTO() {
		
	}
	

	public SessionDTO(String login, String token, Date tokenExpirationTime, Date tokenExpeditionTime, List<Account> accounts, List<AccountPlan> plans, UserSessionDTO userdto) {

		super();
		this.login = login;
		this.token = token;
		this.tokenExpirationTime = tokenExpirationTime;
		this.tokenExpeditionTime = tokenExpeditionTime;
		this.accounts = accounts;
		this.plans = plans;
		this.user = userdto;
	}

	
	
	
	public UserSessionDTO getUser() {
		return user;
	}


	public void setUser(UserSessionDTO user) {
		this.user = user;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpirationTime() {
		return tokenExpirationTime;
	}

	public void setTokenExpirationTime(Date tokenExpirationTime) {
		this.tokenExpirationTime = tokenExpirationTime;
	}
	
	public Date getTokenExpeditionTime() {
		return tokenExpeditionTime;
	}

	public void setTokenExpeditionTime(Date tokenExpeditionTime) {
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	

}
