package com.gama.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.gama.model.Account;
import com.gama.model.Usuario;


public class SessionDTO {
	private String token;
	private LocalDateTime tokenExpirationDate;
	private Usuario user;
	private List<Account> accounts;

	// TODO: Lista de plano de contas
	
	public SessionDTO() {
		
	}
	
	public SessionDTO(Usuario user, String token, LocalDateTime tokenExpirationDate, List accounts) {
		super();
		this.user = user;
		this.token = token;
		this.tokenExpirationDate = tokenExpirationDate;
		this.accounts = accounts;
	}


	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getTokenExpirationDate() {
		return tokenExpirationDate;
	}

	public void setTokenExpirationDate(LocalDateTime tokenExpirationDate) {
		this.tokenExpirationDate = tokenExpirationDate;
	}

	public List getAccounts() {
		return accounts;
	}

	public void setAccounts(List accounts) {
		this.accounts = accounts;
	}
	
	

}
