package com.gama.model.dto;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.gama.enums.AccountType;
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
	private String transactionType;
	
	@ApiModelProperty(value = "Descrição do Plano de conta", required = true) //Receives ID of Account Plan
	private String accountPlanDescription;
	
	@ApiModelProperty(value = "Conta de origem") //Receives name of account holder
	private String sourceAccountName;
	
	@ApiModelProperty(value = "Tipo da Conta de Origem") //receives type of account. Together with accountName, used to find the specific account
	private String sourceAccountType;
	
	@ApiModelProperty(value = "Conta de destino")
	private String destinationAccountName;
	
	@ApiModelProperty(value = "Tipo da Conta de Destino")
	private String destinationAccountType;
	

	@ApiModelProperty(value = "Data", example = "01/01/2021", required = true)
	private LocalDate date;
	
	@ApiModelProperty(value = "Valor", example = "0,00", required = true)
	private Double value;
	
	@ApiModelProperty(value = "Descrição", required = true)
	private String description;		
	
	
	
	public TransactionDTO()
	{
		
	}
	
	public TransactionDTO(String transactionType, String accountPlanDescription, String sourceAccountName, String sourceAccountType,
			String destinationAccountName, String destinationAccountType, LocalDate date, Double value, String description) {
	
		this.transactionType = transactionType;
		this.accountPlanDescription = accountPlanDescription;
		this.sourceAccountName = sourceAccountName;
		this.sourceAccountType = sourceAccountType;
		this.destinationAccountName = destinationAccountName;
		this.destinationAccountType = destinationAccountType;
		this.date = date;
		this.value = value;
		this.description = description;
	}


	public static TransactionDTO transformToTransactionDTO(Transaction transaction, TransactionType transactionType)
	{
		String sourceNumber = transaction.getSourceAccount()==null? null:transaction.getSourceAccount().getNumero();
		String destinationNumber = transaction.getDestinationAccount()==null? null:transaction.getDestinationAccount().getNumero();
		
		String sourceType = sourceNumber==null? null: AccountType.getAccountTypeString(transaction.getSourceAccount().getTipo());
		String destinationType = destinationNumber==null? null: AccountType.getAccountTypeString(transaction.getDestinationAccount().getTipo());
		
		return new TransactionDTO(TransactionType.typeToString(transactionType), transaction.getAccountPlan().getDescription(),
				sourceNumber, sourceType, destinationNumber, destinationType, transaction.getDate(), 
				transaction.getValue(), transaction.getDescription());
	}
	

	
	public static Transaction transformToTransaction(TransactionDTO transactionDTO, AccountRepository acRepo, 
			AccountPlanRepository apr)
	{
		
		Account sourceAccount = transactionDTO.getSourceAccountType() == null ? 
				null : 
				acRepo.findByNumeroAndTipo(transactionDTO.getSourceAccountName(), AccountType.getAccountType(transactionDTO.getSourceAccountType()));
		Account destinationAccount = transactionDTO.getDestinationAccountType() == null ? 
				null : 
				acRepo.findByNumeroAndTipo(transactionDTO.getDestinationAccountName(), AccountType.getAccountType(transactionDTO.getDestinationAccountType()));

		AccountPlan acp = null;
		if(sourceAccount==null)
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
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	
	public String getSourceAccountType() {
		return sourceAccountType;
	}

	public void setSourceAccountType(String sourceAccountType) {
		this.sourceAccountType = sourceAccountType;
	}

	public String getDestinationAccountType() {
		return destinationAccountType;
	}

	public void setDestinationAccountType(String destinationAccountType) {
		this.destinationAccountType = destinationAccountType;
	}
	
}
