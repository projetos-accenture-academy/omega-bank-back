package com.gama.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gama.model.Conta;
import com.gama.model.Lancamento;


@Repository
public interface LancamentoRepository extends CrudRepository<Lancamento, Integer> 
{
	
	boolean existsById(Long id);
	Lancamento findById(Long id);
	
	List<Lancamento> findByContaOrigem(Conta conta);
	
	List<Lancamento> findByContaDestino(Conta conta);
	
	List<Lancamento> findByData(LocalDate data);
	
	//TODO: Make query by date RANGE
	 
	@Query(nativeQuery = true, value = "select * from Lancamento l where l.conta_origem= :id_conta and data between :start_date and :end_date;")
	List<Lancamento> findLancamentoByContaOrigemBetweenData(@Param("id_conta") Long idContaOrigem, @Param("start_date") LocalDate data_start, @Param("end_date") LocalDate data_end);
	
	@Query(nativeQuery = true, value = "select * from Lancamento l where l.conta_destino= :id_conta and data between :start_date and :end_date;")
	List<Lancamento> findLancamentoByContaDestinoBetweenData(@Param("id_conta") Long idContaDestino, @Param("start_date") LocalDate data_start, @Param("end_date") LocalDate data_end);
	
}
