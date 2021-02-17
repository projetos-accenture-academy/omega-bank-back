package com.gama.controller;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gama.enums.TransactionType;
import com.gama.model.Account;
import com.gama.model.Transaction;
import com.gama.model.User;
import com.gama.model.dto.TransactionDTO;
import com.gama.model.dto.response.TransactionDTOResponse;
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
	
	@PostMapping(produces="application/json", consumes="application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String novaTransacao(@RequestBody TransactionDTO transactionDTO, TransactionType type) {
		try {
			transactionService.addTransaction(modelMapper.map(transactionDTO, Transaction.class), type);
			return "Transação realizada com sucesso!";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@GetMapping(path = "/{id}", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public TransactionDTOResponse getTransaction(Long id)	{
		return modelMapper.map(transactionService.getTransaction(id), TransactionDTOResponse.class);
	}

	@GetMapping(path = "/all", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<Transaction> getAllTransactions()
	{
		return transactionService.getAllTransactions();
	}

	@GetMapping(path = "/{sourceAccount}", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<Transaction> getTransactionsBySourceAccount(Account sourceAccount)
	{
		return transactionService.getTransactionsBySourceAccount(sourceAccount);
	}
	
	@GetMapping(path = "/{destinationAccount}", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<Transaction> getTransactionsByDestinationAccount(Account destinationAccount)
	{
		return transactionService.getTransactionsByDestinationAccount(destinationAccount);
	}
	
	@GetMapping(path = "/{date}", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<Transaction> getTransactionsByDate(LocalDate date)
	{
		return transactionService.getTransactionsByDate(date);
	}
	
	@GetMapping(path = "/{byDateRange}", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<Transaction> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate)
	{
		return transactionService.getTransactionsByDateRange(startDate, endDate);
	}

	@GetMapping(path = "/{byAccountSourceAndDate}", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<Transaction> getTransactionsBySourceAccountAndDateBetween(Account sourceAccount, LocalDate startDate, LocalDate endDate)
	{
		return transactionService.getTransactionsBySourceAccountAndDateBetween(sourceAccount, startDate, endDate);
	}
	
	@GetMapping(path = "/{byAccountDestinationAndDate}", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<Transaction> getTransactionsByDestinationAccountAndDateBetween(Account destinationAccount, LocalDate startDate, LocalDate endDate)
	{
		return transactionService.getTransactionsByDestinationAccountAndDateBetween(destinationAccount, startDate, endDate);
	}
	
	@GetMapping(path = "/{inAccountPlan}", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<CategorizedTransactionAuxiliary> getIngoingTransactionsCategorizedByAccountPlan(Long idIngoingAccount, LocalDate startDate, LocalDate endDate)
	{
		return transactionService.getIngoingTransactionsCategorizedByAccountPlan(idIngoingAccount, startDate, endDate);
	}
	
	@GetMapping(path = "/{outAccountSourceAndDate}", produces="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public Iterable<CategorizedTransactionAuxiliary> getOutgoingTransactionsCategorizedByAccountPlan(Long idOutgoingAccount, LocalDate startDate, LocalDate endDate)
	{
		return transactionService.getOutgoingTransactionsCategorizedByAccountPlan(idOutgoingAccount, startDate, endDate);
	}

}
