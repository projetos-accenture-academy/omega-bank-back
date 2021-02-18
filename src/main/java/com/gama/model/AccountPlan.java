package com.gama.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gama.enums.TransactionType;
import com.sun.istack.NotNull;

@Entity
@Table(name = "planos_conta")
public class AccountPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@NotNull
	private User user;

	@Column(nullable = false, name = "descricao")
	private String description;

	@Column(nullable = false, name = "tipo")
	private TransactionType type;

	public AccountPlan() {
	}

	/**
	 * Cria um plano de conta criado por um Usuário específico, com uma descrição
	 * não-nula
	 * 
	 * @param user
	 * @param description
	 */
	public AccountPlan(User user, String description, TransactionType type) {
		this.user = user;
		this.description = description;
		this.type = type;
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

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

}
