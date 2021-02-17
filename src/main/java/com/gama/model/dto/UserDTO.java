package com.gama.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserDTO {

    @ApiModelProperty(value = "Login", example = "user01", required = true)
	private String login;	

    @ApiModelProperty(value = "Senha", required = true)
	private String senha;

    @ApiModelProperty(value = "Nome", example = "user", required = true)
	private String nome;

    @ApiModelProperty(value = "CPF", example = "11111111111", required = true)
	private String cpf;
	
    @ApiModelProperty(value = "Telefone", example = "6735620000")
	private String telefone;	
		
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
