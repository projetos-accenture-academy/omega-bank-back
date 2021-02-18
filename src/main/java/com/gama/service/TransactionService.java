package com.gama.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gama.enums.TransactionType;
import com.gama.exceptions.TransactionAlreadyExistsException;
import com.gama.model.Account;
import com.gama.model.Transaction;
import com.gama.model.dto.TransactionDTO;
import com.gama.repository.AccountPlanRepository;
import com.gama.repository.AccountRepository;
import com.gama.repository.TransactionRepository;
import com.gama.utils.CategorizedTransactionAuxiliary;
import com.gama.utils.Validator;

@Component
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountPlanRepository accountPlanRepository;
	
	
	/**
	 * Retorna o Tipo de Transação de uma Transação a partir da nulidade de suas contas de origem/destino
	 * @param t
	 * @return
	 */
	public TransactionType getTransactionType(Transaction t) throws IllegalArgumentException, Exception
	{
		if(t.getSourceAccount()==null && t.getDestinationAccount()!=null)
		{
			//Deposit
			return TransactionType.R;
		}
		else if(t.getSourceAccount()!=null && t.getDestinationAccount()==null)
		{
			//Withdrawal
			return TransactionType.D;
		}
		else if(t.getSourceAccount()!=null && t.getDestinationAccount()==null)
		{
			return TransactionType.T;
		}
		else
			throw new IllegalArgumentException("Erro: Valores inválidos de nulidade de Contas de Origem e Destino");
		
	}
	
	
	
	/**
	 * Realiza a inserção de um Lançamento (Não é permitido atualização de um Lançamento)
	 * 
	 * @param transaction Lançamento a ser inserido
	 * @param transactionType Tipo de lançamento a ser inserido
	 * @throws LançamentoExistenteException
	 */
	public void addTransaction(TransactionDTO transaction, TransactionType transactionType) throws IllegalArgumentException, TransactionAlreadyExistsException, Exception
	{
		if(transaction !=null)
		{
			Validator.isEmptyValue(transaction.getAccountPlanDescription(), "O Lançamento necessita de um tipo definido");
			Validator.isEmptyValue(transaction.getDate(), "O Lançamento necessita de uma data definida");
			Validator.isEmptyValue(transaction.getValue(), "O Lançamento necessita de um valor definido");
			
			//Validates null accounts
			if(transaction.getDestinationAccountName()==null && transaction.getSourceAccountName()==null)
			{
				throw new IllegalArgumentException("O Lançamento necessita de ao menos uma conta definida");
			}
		
			
			//Based on transaction type, check if the source/destination value is valid
			switch(transactionType)
			{
			case T:
				if(transaction.getDestinationAccountName()==null || transaction.getSourceAccountName()==null)
				{
					throw new IllegalArgumentException("O Lançamento(Tranferência) necessita de contas de origem e destino definidas");
				}
				break;
			case D:
				if(transaction.getDestinationAccountName()!=null || transaction.getSourceAccountName()==null)
				{
					throw new IllegalArgumentException("O Lançamento(Despesa) necessita de conta de origem definida e destino nula");
				}
				break;
			case R:
				if(transaction.getDestinationAccountName()==null || transaction.getSourceAccountName()!=null)
				{
					throw new IllegalArgumentException("O Lançamento(Receita) necessita de conta de origem nula e destino definida");
				}
				break;
			default:
				throw new IllegalArgumentException("Tipo de Transação (" + transactionType.toString() + ") inválido");
				
			}
			
			//If money is coming into user account, AccountPlan is a default one
			transaction.setAccountPlanDescription("DEFAULT_RECEITA");
			
			//TODO: check if this may cause an error
			boolean lancamentoExistente = transactionRepository.existsById(transaction.getId());
			
			if(!lancamentoExistente) //There cannot be an update, only an insertion
			{
				transactionRepository.save(TransactionDTO.transformToTransaction(transaction, accountRepository, accountPlanRepository));
			}
			else
			{
				throw new TransactionAlreadyExistsException("Lançamento nº" + transaction.getId() + " já existe.");
			}
		}
	}
	

	/**
	 * Encontra um lançamento a partir de seu ID
	 * @param id
	 * @return Um Objeto Lançamento com o id especificado
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	public TransactionDTO getTransaction(Long id) throws IllegalArgumentException, Exception
	{
		Transaction t = transactionRepository.findById(id);
		return TransactionDTO.transformToTransactionDTO(t, getTransactionType(t));
	}
		
	
	/**
	 * Obtém todos os lançamentos existentes
	 * @return Uma lista com todos os lançamentos existentes na base de dados
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	public Iterable<TransactionDTO> getAllTransactions() throws IllegalArgumentException, Exception
	{
		Iterable<Transaction> transactions = transactionRepository.findAll();
		List<TransactionDTO> transactionDTOs = new ArrayList<TransactionDTO>();
		
		for(Transaction t: transactions)
		{
			//transforms each into a DTO
			transactionDTOs.add(TransactionDTO.transformToTransactionDTO(t, getTransactionType(t)));
		}
			
		return transactionDTOs;
	}

	/**
	 * Obtém todos os lançamentos de uma conta origem específica 
	 * @param sourceAccountName: nome único da conta que enviou os lançamentos buscados
	 * @return Uma lista com todos os lançamentos que possuem uma conta de origem especificada
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	public Iterable<TransactionDTO> getTransactionsBySourceAccount(String sourceAccountName) throws IllegalArgumentException, Exception
	{
		Account sourceAccount = accountRepository.findByNumero(sourceAccountName);
		
		Iterable<Transaction> transactions = transactionRepository.findBySourceAccount(sourceAccount);
		
		List<TransactionDTO> transactionDTOs = new ArrayList<TransactionDTO>();
		
		for(Transaction t: transactions)
		{
			//transforms each into a DTO
			transactionDTOs.add(TransactionDTO.transformToTransactionDTO(t, getTransactionType(t)));
		}
			
		return transactionDTOs;
		
	}
	
	/**
	 * Obtém todos os lançamentos de uma conta destino específica
	 * @param contaOrigem: Conta que recebeu os lançamentos buscados
	 * @return Uma lista com todos os lançamentos que possuem uma conta de destino especificada
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	public Iterable<TransactionDTO> getTransactionsByDestinationAccount(String destinationAccountName) throws IllegalArgumentException, Exception
	{
		Account destinationAccount = accountRepository.findByNumero(destinationAccountName);
		
		Iterable<Transaction> transactions = transactionRepository.findByDestinationAccount(destinationAccount);
		
		List<TransactionDTO> transactionDTOs = new ArrayList<TransactionDTO>();
		
		for(Transaction t: transactions)
		{
			//transforms each into a DTO
			transactionDTOs.add(TransactionDTO.transformToTransactionDTO(t, getTransactionType(t)));
		}
			
		return transactionDTOs;
	}
	
	
	
	/**
	 * Obtém todos os lançamentos dentro de uma data específica
	 * @param data
	 * @return
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	public Iterable<TransactionDTO> getTransactionsByDate(LocalDate date) throws IllegalArgumentException, Exception
	{
		
		Iterable<Transaction> transactions = transactionRepository.findByDate(date);
		
		List<TransactionDTO> transactionDTOs = new ArrayList<TransactionDTO>();
		
		for(Transaction t: transactions)
		{
			//transforms each into a DTO
			transactionDTOs.add(TransactionDTO.transformToTransactionDTO(t, getTransactionType(t)));
		}
			
		return transactionDTOs;
	}
	
	
	
	/**
	 * Obtém todos os lançamentos dentro de um intervalo de datas
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	public Iterable<TransactionDTO> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate) throws IllegalArgumentException, Exception
	{
		Iterable<Transaction> transactions = transactionRepository.findByDateBetween(startDate, endDate);
		
		List<TransactionDTO> transactionDTOs = new ArrayList<TransactionDTO>();
		
		for(Transaction t: transactions)
		{
			//transforms each into a DTO
			transactionDTOs.add(TransactionDTO.transformToTransactionDTO(t, getTransactionType(t)));
		}
			
		return transactionDTOs;
	}
	
	
	/**
	 * Obtém todos os lançamentos de uma conta origem específica dentro de um intervalo de datas
	 * @param sourceAccount ID da conta origem dos Lançamentos
	 * @param startDate Data de início da busca
	 * @param endDate Data de fim da busca
	 * @return Lista com todos os lançamentos entre as datas de busca com a conta de origem requisitada
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	public Iterable<TransactionDTO> getTransactionsBySourceAccountAndDateBetween(String sourceAccountName, LocalDate startDate, LocalDate endDate) throws IllegalArgumentException, Exception
	{
		Account sourceAccount = accountRepository.findByNumero(sourceAccountName);

		
		Iterable<Transaction> transactions = transactionRepository.findBySourceAccountAndDateBetween(sourceAccount, startDate, endDate);
		
		List<TransactionDTO> transactionDTOs = new ArrayList<TransactionDTO>();
		
		for(Transaction t: transactions)
		{
			//transforms each into a DTO
			transactionDTOs.add(TransactionDTO.transformToTransactionDTO(t, getTransactionType(t)));
		}
			
		return transactionDTOs;
		
	}
	
	
	/**
	 * Obtém todos os lançamentos de uma conta destino específica dentro de um intervalo de datas
	 * @param sourceAccount Conta destino dos Lançamentos
	 * @param startDate Data de início da busca
	 * @param endDate Data de fim da busca
	 * @return Lista com todos os lançamentos entre as datas de busca com a conta de destino requisitada
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	public Iterable<TransactionDTO> getTransactionsByDestinationAccountAndDateBetween(String destinationAccountName, LocalDate startDate, LocalDate endDate) throws IllegalArgumentException, Exception
	{

		Account destinationAccount = accountRepository.findByNumero(destinationAccountName);

		
		Iterable<Transaction> transactions = transactionRepository.findByDestinationAccountAndDateBetween(destinationAccount, startDate, endDate);
		
		List<TransactionDTO> transactionDTOs = new ArrayList<TransactionDTO>();
		
		for(Transaction t: transactions)
		{
			//transforms each into a DTO
			transactionDTOs.add(TransactionDTO.transformToTransactionDTO(t, getTransactionType(t)));
		}
			
		return transactionDTOs;

		
		
	}
	
	
	/**
	 * Retorna uma lista com a soma dos valores dos lançamentos que entraram em uma determinada conta, categorizados por plano de 
	 * conta, dentro de um intervalo de tempo
	 * @param idIngoingAccount ID numérico da conta a ser buscada
	 * @param startDate Data de início da busca
	 * @param endDate Data de fim da busca
	 * @return
	 */
	public Iterable<CategorizedTransactionAuxiliary> getIngoingTransactionsCategorizedByAccountPlan(Long idIngoingAccount, LocalDate startDate, LocalDate endDate)
	{
		return transactionRepository.findIngoingValueSumByCategorizedAccountPlan(idIngoingAccount, startDate, endDate);
	}
	
	/**
	 * Retorna uma lista com a soma dos valores dos lançamentos que saíram de uma determinada conta, categorizados por plano de 
	 * conta, dentro de um intervalo de tempo
	 * @param idOutgoingAccount ID numérico da conta a ser buscada
	 * @param startDate Data de início da busca
	 * @param endDate Data de fim da busca
	 * @return
	 */
	public Iterable<CategorizedTransactionAuxiliary> getOutgoingTransactionsCategorizedByAccountPlan(Long idOutgoingAccount, LocalDate startDate, LocalDate endDate)
	{
		return transactionRepository.findOutgoingValueSumByCategorizedAccountPlan(idOutgoingAccount, startDate, endDate);
	}
	
}
