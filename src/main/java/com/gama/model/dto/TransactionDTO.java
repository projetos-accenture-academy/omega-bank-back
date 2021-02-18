package com.gama.model.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.gama.enums.TransactionType;
import com.gama.model.Account;
import com.gama.model.AccountPlan;
import com.gama.model.Transaction;
import com.gama.model.User;
import com.gama.repository.AccountPlanRepository;
import com.gama.repository.AccountRepository;
import com.gama.repository.UserRepository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//DTO That receives transaction informartion from User to add it to DB
@ApiModel
public class TransactionDTO {
	


	@ApiModelProperty(value = "ID da movimentação")
	private Long id=null;
	
	@ApiModelProperty(value = "Tipo", required = true)
	private TransactionType transactionType;
	
	@ApiModelProperty(value = "Descrição do Plano de conta", required = true) //Receives ID of Account Plan
	private String accountPlanDescription;
	
	@ApiModelProperty(value = "Conta de origem") //Receives ID of accountPlan
	private String sourceAccountName;
	
	@ApiModelProperty(value = "Conta de destino")
	private String destinationAccountName;
	
	@ApiModelProperty(value = "Data", example = "01/01/2021", required = true)
	private Date date;
	
	@ApiModelProperty(value = "Valor", example = "0,00", required = true)
	private Double value;
	
	@ApiModelProperty(value = "Descrição", required = true)
	private String description;		
	
	
	
	public TransactionDTO()
	{
		
	}
	
	public TransactionDTO(TransactionType transactionType, String accountPlanDescription, String sourceAccountName,
			String destinationAccountName, Date date, Double value, String description) {
	
		this.transactionType = transactionType;
		this.accountPlanDescription = accountPlanDescription;
		this.sourceAccountName = sourceAccountName;
		this.destinationAccountName = destinationAccountName;
		this.date = date;
		this.value = value;
		this.description = description;
	}


	public static TransactionDTO transformToTransactionDTO(Transaction transaction, TransactionType transactionType)
	{
		return new TransactionDTO(transactionType, transaction.getAccountPlan().getDescription(),
				transaction.getSourceAccount().getNumero(), transaction.getDestinationAccount().getNumero(), transaction.getDate(), 
				transaction.getValue(), transaction.getDescription());
				
 
	}
	

	
	public static Transaction transformToTransaction(TransactionDTO transactionDTO, AccountRepository acRepo, 
			AccountPlanRepository apr)
	{
		
		Account sourceAccount = acRepo.findByNumero(transactionDTO.getSourceAccountName());
		Account destinationAccount = acRepo.findByNumero(transactionDTO.getDestinationAccountName());

		AccountPlan acp = null;
		if(sourceAccount==null || sourceAccount.getUsuario()==null)
		{
			//Money deposit, no source, so AccountPlan has a default value from the DESTINATION account
			acp = apr.findByUserAndDescription(destinationAccount.getUsuario(), transactionDTO.getAccountPlanDescription());
		}
		else //transaction or withdrawal, so it gets Account plan from the source, as it should
		{
			acp = apr.findByUserAndDescription(sourceAccount.getUsuario(), transactionDTO.getAccountPlanDescription());
		}
		 
		
		
		return new Transaction(acp, sourceAccount, destinationAccount, transactionDTO.getDate(), transactionDTO.getValue(), 
				transactionDTO.getDescription());
	}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	
	
	public String getAccountPlanDescription() {
		return accountPlanDescription;
	}

	public void setAccountPlanDescription(String accountPlanDescription) {
		this.accountPlanDescription = accountPlanDescription;
	}

	public String getSourceAccountName() {
		return sourceAccountName;
	}

	public void setSourceAccountName(String sourceAccount) {
		this.sourceAccountName = sourceAccount;
	}

	public String getDestinationAccountName() {
		return destinationAccountName;
	}

	public void setDestinationAccountName(String destinationAccount) {
		this.destinationAccountName = destinationAccount;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	
}
