package com.gama.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserSessionDTO {

	@ApiModelProperty(value = "ID", example = "0", required = true)
	private Integer id;
	
	@ApiModelProperty(value = "Login", example = "user01", required = true)
	private String login;

	@ApiModelProperty(value = "Nome", example = "user", required = true)
	private String nome;

	@ApiModelProperty(value = "CPF", example = "11111111111", required = true)
	private String cpf;

	@ApiModelProperty(value = "Telefone", example = "6735620000")
	private String telefone;
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
