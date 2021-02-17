package com.gama.model.dto;

import com.gama.model.AccountPlan;
import com.gama.model.User;

public class AccountPlanDTO {

	private Integer id;
	private User user;
	private String description;
	
	
	public AccountPlanDTO()
	{
		
	}
	
	public AccountPlanDTO(Integer id, User user, String description)
	{
		this.id=id;
		this.user=user;
		this.description=description;
	}
	
	public static AccountPlanDTO transformToDTO(AccountPlan ap)
	{
		return new AccountPlanDTO(ap.getId(), ap.getuser(), ap.getdescription());
		
	}

	public static AccountPlan transformToObject(AccountPlanDTO apd)
	{
		return new AccountPlan(apd.getUser(), apd.getDescription());

	}
	public Integer getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
