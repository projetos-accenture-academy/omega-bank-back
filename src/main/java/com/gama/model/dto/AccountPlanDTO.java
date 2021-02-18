package com.gama.model.dto;

public class AccountPlanDTO {

	private int usuario_id;
	private String description;
	
	public int getUserId() {
		return usuario_id;
	}

	public void setUserId(int userId) {
		this.usuario_id = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
