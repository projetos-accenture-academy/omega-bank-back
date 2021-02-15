package com.gama.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gama.model.Account;
import com.gama.model.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> 
{
	
	boolean existsById(Long id);
	Transaction findById(Long id);
	

	List<Transaction> findByDate(LocalDate data);
	
	List<Transaction> findByDateBetween(LocalDate startDate, LocalDate endDate);
	
	
	List<Transaction> findBySourceAccount(Account sourceAccount);
	
	List<Transaction> findByDestinationAccount(Account destinationAccount);
	
	
	List<Transaction> findBySourceAccountAndDateBetween(Account sourceAccount, LocalDate startDate, LocalDate endDate);

	List<Transaction> findByDestinationAccountAndDateBetween(Account destinationAccount, LocalDate startDate, LocalDate endDate);
	
	 
//	@Query(nativeQuery = true, value = "select * from Lancamento l where l.conta_origem= :id_conta and data between :start_date and :end_date;")
//	List<Transaction> findLancamentoByContaOrigemBetweenData(@Param("id_conta") Long idContaOrigem, @Param("start_date") LocalDate data_start, @Param("end_date") LocalDate data_end);
//	
//	@Query(nativeQuery = true, value = "select * from Lancamento l where l.conta_destino= :id_conta and data between :start_date and :end_date;")
//	List<Transaction> findLancamentoByContaDestinoBetweenData(@Param("id_conta") Long idContaDestino, @Param("start_date") LocalDate data_start, @Param("end_date") LocalDate data_end);
	
}
