package com.gama.controller;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gama.enums.TransactionType;
import com.gama.exceptions.TransactionAlreadyExistsException;
import com.gama.model.Account;
import com.gama.model.AccountPlan;
import com.gama.model.Transaction;
import com.gama.model.User;
import com.gama.model.dto.AccountPlanDTO;
import com.gama.model.dto.TransactionDTO;
import com.gama.service.TransactionService;
import com.gama.utils.CategorizedTransactionAuxiliary;

@Controller
@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private ModelMapper modelMapper = new ModelMapper();
	
	//Wrapper class for request body
	public class AccountInfo
	{
		public String accountName;
		public String accountType;
	}
	
	
	//Wrapper class for request body
	public class AccountInfoDateRange
	{
		public String accountName;
		public String accountType;
		public LocalDate startDate;
		public LocalDate endDate;
		
	}
	
	//--------------------------------------------------------GET---------------------------------------------------------------
	
	
	/**
	 * Retorna um Lançamento específico a partir de seu ID
	 * @param id
	 * @return
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	@GetMapping(path = "/{id}", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public TransactionDTO getTransaction(@PathVariable(name = "id") Long id) throws IllegalArgumentException, Exception	{
		return transactionService.getTransaction(id);
		
	}
	
	
	/**
	 * Retorna TODOS os Lançamentos existentes na base (Apenas para teste)
	 * @return Uma Lista com todos os Lançamentos na Base de Dados
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@GetMapping(path = "/all", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<TransactionDTO> getAllTransactions() throws IllegalArgumentException, Exception
	{
		return transactionService.getAllTransactions();
	}

	/**
	 * Retorna todas as Transações que tenham como ContaOrigem com nome/número equivalente ao passado para a função
	 * @param sourceAccount Classe com nome e tipo da Conta de Origem
	 * @return Uma lista com TODAS as Transações da conta Origem especificada
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@GetMapping(path = "/sourceAccount", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<TransactionDTO> getTransactionsBySourceAccount(@RequestBody AccountInfo sourceAccount) 
			throws IllegalArgumentException, Exception
	{
		return transactionService.getTransactionsBySourceAccount(sourceAccount.accountName, sourceAccount.accountType);
	}
	
	
	
	/**
	 * Retorna todas as Transações que tenham como Conta de Destino com nome/número equivalente ao passado para a função
	 * @param destinationAccount Classe com nome e tipo da Conta de Destino
	 * @return Uma lista com TODAS as Transações da conta Destino especificada
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	@GetMapping(path = "/destinationAccount", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<TransactionDTO> getTransactionsByDestinationAccount(@RequestBody AccountInfo destinationAccount) 
			throws IllegalArgumentException, Exception
	{
		return transactionService.getTransactionsByDestinationAccount(destinationAccount.accountName, destinationAccount.accountType);
	}
	
	
	
	
	
	/**
	 * Retorna todas as Transações na Base de dados que tenham data equivalente a passada para a função
	 * @param date Data escolhida
	 * @return Uma lista com TODAS as Transações na base com a data especificada
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	@GetMapping(path = "/date", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<TransactionDTO> getTransactionsByDate(@RequestBody LocalDate date) throws IllegalArgumentException, Exception
	{
		return transactionService.getTransactionsByDate(date);
	}
	
	
	
	/**
	 * Retorna todas as Transações na Base de dados que estejam entre a data de inicio e de fim da busca
	 * @param startDate Data de início da busca
	 * @param endDate Data de fim da busca
	 * @return
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@GetMapping(path = "/dateRange", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<TransactionDTO> getTransactionsByDateRange(@RequestBody LocalDate startDate, @RequestBody LocalDate endDate) throws IllegalArgumentException, Exception
	{
		return transactionService.getTransactionsByDateRange(startDate, endDate);
	}

	
	/**
	 * Retorna todas as Transações que tenham uma específica Conta Origem e estejam entre a data de inicio e de fim da busca
	 * @param sourceAccount Classe que contém o nome e tipo da conta de origem, junto com as datas de início e fim da busca 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@GetMapping(path = "/sourceAccount/dateRange", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<TransactionDTO> getTransactionsBySourceAccountAndDateBetween(@RequestBody AccountInfoDateRange sourceAccount) 
			throws IllegalArgumentException, Exception
	{
		return transactionService.getTransactionsBySourceAccountAndDateBetween(sourceAccount.accountName, 
				sourceAccount.accountType, sourceAccount.startDate, sourceAccount.endDate);
	}
	
	
	/**
	 * Retorna todas as Transações que tenham uma específica Conta Destino e estejam entre a data de inicio e de fim da busca
	 * @param destinationAccount Classe que contém o nome e tipo da conta de destino, junto com as datas de início e fim da busca
	 * @param startDate
	 * @param endDate
	 * @return Uma lista com todas as transações que estejam dentro das condições delimitadas
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */	
	@GetMapping(path = "/destinationAccount/dateRange", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<TransactionDTO> getTransactionsByDestinationAccountAndDateBetween(@RequestBody AccountInfoDateRange destinationAccount) 
			throws IllegalArgumentException, Exception
	{
		return transactionService.getTransactionsByDestinationAccountAndDateBetween(destinationAccount.accountName, 
				destinationAccount.accountType, destinationAccount.startDate, destinationAccount.endDate);
	}
	
	
	/**
	 * Retorna uma lista de soma de valores Transações que entraram em uma conta dentro de um intervalo de datas, categorizadas por Plano de Conta
	 * @param idIngoingAccount
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@GetMapping(path = "/ingoingCategorizedTransactions", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<CategorizedTransactionAuxiliary> getIngoingTransactionsCategorizedByAccountPlan(Long idIngoingAccount, LocalDate startDate, LocalDate endDate)
	{
		return transactionService.getIngoingTransactionsCategorizedByAccountPlan(idIngoingAccount, startDate, endDate);
	}
	
	/**
	 * Retorna uma lista de soma de valores Transações que saíram em uma conta dentro de um intervalo de datas, categorizadas por Plano de Conta
	 * @param idOutgoingAccount
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@GetMapping(path = "/outgoingCategorizedTransactions", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<CategorizedTransactionAuxiliary> getOutgoingTransactionsCategorizedByAccountPlan(Long idOutgoingAccount, LocalDate startDate, LocalDate endDate)
	{
		return transactionService.getOutgoingTransactionsCategorizedByAccountPlan(idOutgoingAccount, startDate, endDate);
	}

	
	
	
	
	//--------------------------------------------------------POST---------------------------------------------------------------
	
	/**
	 * Cria um novo Lançamento na base de dados
	 * @param transactionDTO
	 * @param type
	 * @return
	 * @throws Exception 
	 * @throws TransactionAlreadyExistsException 
	 * @throws IllegalArgumentException 
	 */
	@PostMapping(produces="application/json", consumes="application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) throws IllegalArgumentException, TransactionAlreadyExistsException, Exception {
		
		
		
		/*
		switch(type)
		{
		case "R":
			tt=TransactionType.R;
			break;
		case "D":
			tt=TransactionType.R;
			break;
		case "T":
			tt=TransactionType.R;
			break;
			
		default:
			throw new IllegalArgumentException();
		}*/
		
		transactionService.addTransaction(transactionDTO);
		return new ResponseEntity<>(transactionDTO, HttpStatus.CREATED);
		
	}
	
}
