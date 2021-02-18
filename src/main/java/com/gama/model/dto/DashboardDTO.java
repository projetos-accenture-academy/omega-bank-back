package com.gama.model.dto;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.gama.enums.TransactionType;
import com.gama.model.Account;
import com.gama.model.Transaction;

public class DashboardDTO {
	private List<AccountDTO> accounts;
	private Integer numberOfAccounts;

	@Autowired
	private ModelMapper modelMapper = new ModelMapper();
	
	public DashboardDTO() {
		
	}
	
	public DashboardDTO(List<AccountDTO> accounts, Integer nuberOfAccounts) {
		super();
		this.accounts = accounts;
		this.numberOfAccounts = nuberOfAccounts;
	}

	public List<AccountDTO> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDTO> accounts) {
		this.accounts = accounts;
	}

	public Integer getNumberOfAccounts() {
		return numberOfAccounts;
	}

	public void setNumberOfAccounts(Integer nuberOfAccounts) {
		this.numberOfAccounts = nuberOfAccounts;
	}


	
	public static AccountDTO transformToAccountDTO(Account account, List<TransactionDTO> transactionsDTO )
	{
		return new AccountDTO(account.getId(), account.getTipo(), account.getNumero(), 
				account.getSaldo(), account.getDescricao(), account.getUsuario().getId(), transactionsDTO);
				
 
	}
	
	
}
