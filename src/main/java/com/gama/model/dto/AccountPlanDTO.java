package com.gama.model.dto;

import com.gama.model.AccountPlan;
import com.gama.model.User;
import com.gama.repository.UserRepository;

public class AccountPlanDTO {

	private String login;
	private String description;
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
		
	public static AccountPlan transformToObject(AccountPlanDTO apd, UserRepository userRepo)
	{
		User user = userRepo.findByLogin(apd.getLogin());
		return new AccountPlan(user, apd.getDescription());
		
	}

}
