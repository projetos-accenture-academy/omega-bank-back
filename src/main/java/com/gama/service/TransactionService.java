package com.gama.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gama.exceptions.TransactionAlreadyExistsException;
import com.gama.model.Account;
import com.gama.model.Transaction;
import com.gama.model.dto.TransactionDTO;
import com.gama.repository.TransactionRepository;
import com.gama.utils.Validator;

@Component
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;
	
	/**
	 * Realiza a inserção de um Lançamento
	 * 
	 * @param Lançamento a ser inserido
	 * @throws LançamentoExistenteException
	 */
	public void saveTransaction(TransactionDTO transaction) throws IllegalArgumentException, TransactionAlreadyExistsException, Exception
	{
		if(transaction !=null)
		{
			
			
			Validator.isEmptyValue(transaction.getAccountPlan(), "O Lançamento necessita de um tipo definido");
			Validator.isEmptyValue(transaction.getDate(), "O Lançamento necessita de uma data definida");
			Validator.isEmptyValue(transaction.getValue(), "O Lançamento necessita de um valor definido");
			
			//Validates null accounts
			Validator.isEmptyValue(transaction.getDestinationAccount()==null && transaction.getSourceAccount()==null, 
									"O Lançamento necessita de umdefinido");
			
			
			boolean lancamentoExistente = transactionRepository.existsById(transaction.getId());
			
			if(!lancamentoExistente)
			{
				//TODO
				//transactionRepository.save(transaction);
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
	public Transaction getLancamento(Long id)
	{
		return transactionRepository.findById(id);
	}
	
	
	
	/**
	 * Obtém todos os lançamentos existentes
	 * @return Uma lista com todos os lançamentos existentes na base de dados
	 */
	public Iterable<Transaction> getAllLancamentos()
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
	 * Obtém todos os lançamentos de uma conta origem específica dentro de um intervalo de datas
	 * @param sourceAccount ID da conta origem dos Lançamentos
	 * @param startDate Data de início da busca
	 * @param endDate Data de fim da busca
	 * @return Lista com todos os lançamentos entre as datas de busca com a conta de origem requisitada
	 */
	public Iterable<Transaction> getLancamentoByContaOrigemAndDataBetween(Account sourceAccount, LocalDate startDate, LocalDate endDate)
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
	public Iterable<Transaction> getLancamentoByContaDestinoAndDataBetween(Account destinationAccount, LocalDate startDate, LocalDate endDate)
	{
		return transactionRepository.findBySourceAccountAndDateBetween(destinationAccount, startDate, endDate);
	}
	
	
	
	
	
}
