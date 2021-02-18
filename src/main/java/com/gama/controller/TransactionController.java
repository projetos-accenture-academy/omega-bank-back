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
import com.gama.utils.AccountCategoriesSum;
import com.gama.utils.AccountInfo;
import com.gama.utils.AccountInfoDateRange;
import com.gama.utils.CategorizedTransactionAuxiliary;
import com.gama.utils.DateRange;

@Controller
@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	


	
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
	@PostMapping(path = "/sourceAccount", consumes = "application/json", produces="application/json")
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
	@PostMapping(path = "/destinationAccount", consumes = "application/json", produces="application/json")
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
	@PostMapping(path = "/date", consumes="application/json" , produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<TransactionDTO> getTransactionsByDate(@RequestBody String date) throws IllegalArgumentException, Exception
	{
		LocalDate lDate = LocalDate.parse(date);
		return transactionService.getTransactionsByDate(lDate);
	}
	
	
	
	/**
	 * Retorna todas as Transações na Base de dados que estejam entre a data de inicio e de fim da busca
	 * @param dateRange Datas de início e fim da busca
	 * @return Todas as transações ocorridas neste intervalo de tempo
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@PostMapping(path = "/dateRange", consumes="application/json" , produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<TransactionDTO> getTransactionsByDateRange(@RequestBody DateRange dateRange) 
			throws IllegalArgumentException, Exception
	{
		return transactionService.getTransactionsByDateRange(LocalDate.parse(dateRange.startDate), 
				LocalDate.parse(dateRange.endDate));
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
	@PostMapping(path = "/sourceAccount/dateRange", consumes="application/json", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<TransactionDTO> getTransactionsBySourceAccountAndDateBetween(@RequestBody AccountInfoDateRange sourceAccount) 
			throws IllegalArgumentException, Exception
	{
		return transactionService.getTransactionsBySourceAccountAndDateBetween(sourceAccount.accountName, 
				sourceAccount.accountType, LocalDate.parse(sourceAccount.startDate), LocalDate.parse(sourceAccount.endDate));
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
	@PostMapping(path = "/destinationAccount/dateRange", consumes = "application/json" , produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<TransactionDTO> getTransactionsByDestinationAccountAndDateBetween(@RequestBody AccountInfoDateRange destinationAccount) 
			throws IllegalArgumentException, Exception
	{
		return transactionService.getTransactionsByDestinationAccountAndDateBetween(destinationAccount.accountName, 
				destinationAccount.accountType, LocalDate.parse(destinationAccount.startDate), LocalDate.parse(destinationAccount.endDate));
	}
	
	
	/**
	 * Retorna uma lista de soma de valores Transações que entraram em uma conta dentro de um intervalo de datas, categorizadas por Plano de Conta
	 * @param accountDateInfo Armazena o ID da conta, e as datas de início e fim da requisição
	 * @return
	 */
	@PostMapping(path = "/ingoingCategorizedTransactions", consumes="application/json", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<CategorizedTransactionAuxiliary> getIngoingTransactionsCategorizedByAccountPlan
	(@RequestBody AccountCategoriesSum accountDateInfo)//Long idIngoingAccount, LocalDate startDate, LocalDate endDate)
	{
		
		return transactionService.getIngoingTransactionsCategorizedByAccountPlan(accountDateInfo.accountId, 
				LocalDate.parse(accountDateInfo.startDate), LocalDate.parse(accountDateInfo.endDate));
	}
	
	/**
	 * Retorna uma lista de soma de valores Transações que saíram em uma conta dentro de um intervalo de datas, categorizadas por Plano de Conta
	 * @param accountDateInfo Armazena o ID da conta, e as datas de início e fim da requisição
	 * @return
	 */
	@PostMapping(path = "/outgoingCategorizedTransactions", consumes = "application/json", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<CategorizedTransactionAuxiliary> getOutgoingTransactionsCategorizedByAccountPlan
	(@RequestBody AccountCategoriesSum accountDateInfo)//Long idOutgoingAccount, LocalDate startDate, LocalDate endDate)
	{
		return transactionService.getOutgoingTransactionsCategorizedByAccountPlan(accountDateInfo.accountId, 
				LocalDate.parse(accountDateInfo.startDate), LocalDate.parse(accountDateInfo.endDate));
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
	@CrossOrigin
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
