package com.gama.model.dto;

import com.gama.enums.TransactionType;
import com.gama.model.AccountPlan;
import com.gama.model.User;
import com.gama.repository.UserRepository;

public class AccountPlanDTO {

	private String login;
	private String description;
	private TransactionType type;

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

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public static AccountPlan transformToObject(AccountPlanDTO apd, UserRepository userRepo) {
		User user = userRepo.findByLogin(apd.getLogin());
		return new AccountPlan(user, apd.getDescription(), apd.getType());

	}

}
