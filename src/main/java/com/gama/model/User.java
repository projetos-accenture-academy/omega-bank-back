package com.gama.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tabela de Usuários
 * @author Alessandra
 *
 */

@Entity
@Table(name = "usuarios")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 20)
	private String login;

	@Column(nullable = false, length = 8)
	private String senha;

	@Column(nullable = false, length = 30)
	private String nome;

	@Column(unique = true, nullable = false, length = 11)
	private String cpf;

	public User() {
	}

	public User(String login, String senha, String nome, String cpf) {
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.cpf = cpf;
	}

	public int getId() {
		return id;
	}

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

}
