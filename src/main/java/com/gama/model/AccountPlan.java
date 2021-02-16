package com.gama.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "planos_conta")
public class AccountPlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@NotNull
	private Usuario user;
	
	@Column(nullable = false, name = "descricao")
	private String description;
	
	
	public AccountPlan()
	{
		
	}
	
	
	
	/**
	 * Cria um plano de conta criado por um Usuário específico, com uma descrição não-nula
	 * @param user
	 * @param description
	 */
	public AccountPlan(Usuario user, String description)
	{
		this.user=user;
		this.description=description;
	}


	public Integer getId() {
		return id;
	}


	public Usuario getuser() {
		return user;
	}


	public void setuser(Usuario user) {
		this.user = user;
	}


	public String getdescription() {
		return description;
	}


	public void setdescription(String description) {
		this.description = description;
	}
	
}
