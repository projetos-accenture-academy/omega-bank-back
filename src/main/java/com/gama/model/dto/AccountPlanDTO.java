package com.gama.model.dto;

import com.gama.model.AccountPlan;
import com.gama.model.Usuario;

public class AccountPlanDTO {

	private Integer id;
	private Usuario user;
	private String description;
	
	
	public AccountPlanDTO()
	{
		
	}
	
	public AccountPlanDTO(Integer id, Usuario user, String description)
	{
		this.id=id;
		this.user=user;
		this.description=description;
	}
	
	public static AccountPlanDTO transformToDTO(AccountPlan ap)
	{
		return new AccountPlanDTO(ap.getId(), ap.getuser(), ap.getdescription());
		
	}

	public Integer getId() {
		return id;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
