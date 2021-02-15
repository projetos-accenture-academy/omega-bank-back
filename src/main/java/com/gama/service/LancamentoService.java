package com.gama.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gama.exceptions.LancamentoExistenteException;
import com.gama.model.Account;
import com.gama.model.Transaction;
import com.gama.repository.LancamentoRepository;

@Component
public class LancamentoService {
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	/**
	 * Realiza a inserção de um Lançamento
	 * 
	 * @param Lançamento a ser inserido
	 * @throws LançamentoExistenteException
	 */
	public void AdicionarLancamento(Transaction lancamento) throws LancamentoExistenteException
	{
		boolean lancamentoExistente = lancamentoRepository.existsById(lancamento.getId());
		
		if(!lancamentoExistente)
		{
			lancamentoRepository.save(lancamento);
		}
		else
		{
			throw new LancamentoExistenteException("Lançamento nº" + lancamento.getId() + " já existe.");
		}
	}
	

	/**
	 * Encontra um lançamento a partir de seu ID
	 * @param id
	 * @return Um Objeto Lançamento com o id especificado
	 */
	public Transaction getLancamento(Long id)
	{
		return lancamentoRepository.findById(id);
	}
	
	
	
	/**
	 * Obtém todos os lançamentos existentes
	 * @return Uma lista com todos os lançamentos existentes na base de dados
	 */
	public Iterable<Transaction> getAllLancamentos()
	{
		return lancamentoRepository.findAll();
	}

	/**
	 * Obtém todos os lançamentos de uma conta origem específica 
	 * @param contaOrigem: Conta que enviou os lançamentos buscados
	 * @return Uma lista com todos os lançamentos que possuem uma conta de origem especificada
	 */
	public Iterable<Transaction> getAllLancamentosOrigem(Account contaOrigem)
	{
		return lancamentoRepository.findByContaOrigem(contaOrigem);
	}
	
	/**
	 * Obtém todos os lançamentos de uma conta destino específica
	 * @param contaOrigem: Conta que recebeu os lançamentos buscados
	 * @return Uma lista com todos os lançamentos que possuem uma conta de destino especificada
	 */
	public Iterable<Transaction> getAllLancamentosDestino(Account contaDestino)
	{
		return lancamentoRepository.findByContaDestino(contaDestino);
	}
	
	/**
	 * Obtém todos os lançamentos dentro de uma data específica
	 * @param data
	 * @return
	 */
	public Iterable<Transaction> getLancamentoByData(LocalDate data)
	{
		return lancamentoRepository.findByData(data);
	}
	
	/**
	 * Obtém todos os lançamentos de uma conta origem específica dentro de um intervalo de datas
	 * @param id_conta_origem
	 * @param data_start
	 * @param data_end
	 * @return
	 */
	public Iterable<Transaction> getLancamentoByContaOrigemAndDataBetween(Long id_conta_origem, LocalDate data_start, LocalDate data_end)
	{
		return lancamentoRepository.findLancamentoByContaOrigemBetweenData(id_conta_origem, data_start, data_end);
	}
	
	
	/**
	 * Obtém todos os lançamentos de uma conta destino específica dentro de um intervalo de datas
	 * @param id_conta_destino
	 * @param data_start
	 * @param data_end
	 * @return
	 */
	public Iterable<Transaction> getLancamentoByContaDestinoAndDataBetween(Long id_conta_destino, LocalDate data_start, LocalDate data_end)
	{
		return lancamentoRepository.findLancamentoByContaDestinoBetweenData(id_conta_destino, data_start, data_end);
	}
	
	
	
	
	
}
