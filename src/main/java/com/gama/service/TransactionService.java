package com.gama.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gama.enums.TransactionType;
import com.gama.exceptions.TransactionAlreadyExistsException;
import com.gama.model.Account;
import com.gama.model.Transaction;
import com.gama.repository.TransactionRepository;
import com.gama.utils.CategorizedTransactionAuxiliary;
import com.gama.utils.Validator;

@Component
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	/**
	 * Realiza a inserção de um Lançamento (Não é permitido atualização de um Lançamento)
	 * 
	 * @param transaction Lançamento a ser inserido
	 * @param transactionType Tipo de lançamento a ser inserido
	 * @throws LançamentoExistenteException
	 */
	public void addTransaction(Transaction transaction, TransactionType transactionType) throws IllegalArgumentException, TransactionAlreadyExistsException, Exception
	{
		if(transaction !=null)
		{
			Validator.isEmptyValue(transaction.getAccountPlan(), "O Lançamento necessita de um tipo definido");
			Validator.isEmptyValue(transaction.getDate(), "O Lançamento necessita de uma data definida");
			Validator.isEmptyValue(transaction.getValue(), "O Lançamento necessita de um valor definido");
			
			//Validates null accounts
			if(transaction.getDestinationAccount()==null && transaction.getSourceAccount()==null)
			{
				throw new IllegalArgumentException("O Lançamento necessita de ao menos uma conta definida");
			}
		
			
			//Based on transaction type, check if the source/destination value is valid
			switch(transactionType)
			{
			case T:
				if(transaction.getDestinationAccount()==null || transaction.getSourceAccount()==null)
				{
					throw new IllegalArgumentException("O Lançamento(Tranferência) necessita de contas de origem e destino definidas");
				}
				break;
			case D:
				if(transaction.getDestinationAccount()!=null || transaction.getSourceAccount()==null)
				{
					throw new IllegalArgumentException("O Lançamento(Despesa) necessita de conta de origem definida e destino nula");
				}
				break;
			case R:
				if(transaction.getDestinationAccount()==null || transaction.getSourceAccount()!=null)
				{
					throw new IllegalArgumentException("O Lançamento(Receita) necessita de conta de origem nula e destino definida");
				}
				break;
			default:
				throw new IllegalArgumentException("Tipo de Transação (" + transactionType.toString() + ") inválido");
				
			}
			
			//TODO: check if this may cause an error
			boolean lancamentoExistente = transactionRepository.existsById(transaction.getId());
			
			if(!lancamentoExistente) //There cannot be an update, only an insertion
			{
				transactionRepository.save(transaction);
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
	 */
	public Transaction getTransaction(Long id)
	{
		return transactionRepository.findById(id);
	}
		
	
	/**
	 * Obtém todos os lançamentos existentes
	 * @return Uma lista com todos os lançamentos existentes na base de dados
	 */
	public Iterable<Transaction> getAllTransactions()
	{
		return transactionRepository.findAll();
	}

	/**
	 * Obtém todos os lançamentos de uma conta origem específica 
	 * @param contaOrigem: Conta que enviou os lançamentos buscados
	 * @return Uma lista com todos os lançamentos que possuem uma conta de origem especificada
	 */
	public Iterable<Transaction> getTransactionsBySourceAccount(Account sourceAccount)
	{
		return transactionRepository.findBySourceAccount(sourceAccount);
	}
	
	/**
	 * Obtém todos os lançamentos de uma conta destino específica
	 * @param contaOrigem: Conta que recebeu os lançamentos buscados
	 * @return Uma lista com todos os lançamentos que possuem uma conta de destino especificada
	 */
	public Iterable<Transaction> getTransactionsByDestinationAccount(Account destinationAccount)
	{
		return transactionRepository.findByDestinationAccount(destinationAccount);
	}
	
	/**
	 * Obtém todos os lançamentos dentro de uma data específica
	 * @param data
	 * @return
	 */
	public Iterable<Transaction> getTransactionsByDate(LocalDate date)
	{
		return transactionRepository.findByDate(date);
	}
	
	/**
	 * Obtém todos os lançamentos dentro de um intervalo de datas
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Iterable<Transaction> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate)
	{
		return transactionRepository.findByDateBetween(startDate, endDate);
	}
	
	
	/**
	 * Obtém todos os lançamentos de uma conta origem específica dentro de um intervalo de datas
	 * @param sourceAccount ID da conta origem dos Lançamentos
	 * @param startDate Data de início da busca
	 * @param endDate Data de fim da busca
	 * @return Lista com todos os lançamentos entre as datas de busca com a conta de origem requisitada
	 */
	public Iterable<Transaction> getTransactionsBySourceAccountAndDateBetween(Account sourceAccount, LocalDate startDate, LocalDate endDate)
	{
		return transactionRepository.findBySourceAccountAndDateBetween(sourceAccount, startDate, endDate);
	}
	
	
	/**
	 * Obtém todos os lançamentos de uma conta destino específica dentro de um intervalo de datas
	 * @param sourceAccount Conta destino dos Lançamentos
	 * @param startDate Data de início da busca
	 * @param endDate Data de fim da busca
	 * @return Lista com todos os lançamentos entre as datas de busca com a conta de destino requisitada
	 */
	public Iterable<Transaction> getTransactionsByDestinationAccountAndDateBetween(Account destinationAccount, LocalDate startDate, LocalDate endDate)
	{
		return transactionRepository.findByDestinationAccountAndDateBetween(destinationAccount, startDate, endDate);
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
