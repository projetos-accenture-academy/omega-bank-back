package com.gama.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gama.enums.TransactionType;
import com.gama.model.Account;
import com.gama.model.Transaction;
import com.gama.utils.CategorizedTransactionAuxiliary;


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
	
	//Custom query to sum incoming values divided by category
	@Query(nativeQuery = true, value = "SELECT y.somaValor as valueSum, y.descricao as transactionTypeDescription from "
									+ "( SELECT * FROM (SELECT SUM(valor) as somaValor, descricao as d, id_plano_conta "
									+ "from lancamentos l where l.id_conta_destino= :id_conta and data between :start_date and :end_date "
									+ "GROUP BY id_plano_conta) as x LEFT JOIN planos_conta pc ON x.id_plano_conta = pc.id) as y;")
	List<CategorizedTransactionAuxiliary> findIngoingValueSumByCategorizedAccountPlan(
											@Param("id_conta") Long idContaOrigem,
											@Param("start_date") LocalDate data_start, 
											@Param("end_date") LocalDate data_end);


 
	
	//Custom query to sum incoming values divided by category
	@Query(nativeQuery = true, value = "SELECT y.somaValor as valueSum, y.descricao as transactionTypeDescription from "
									+ "( SELECT * FROM (SELECT SUM(valor) as somaValor, descricao as d, id_plano_conta "
									+ "from lancamentos l where l.id_conta_origem= :id_conta and data between :start_date and :end_date "
									+ "GROUP BY id_plano_conta) as x LEFT JOIN planos_conta pc ON x.id_plano_conta = pc.id) as y;")
	List<CategorizedTransactionAuxiliary> findOutgoingValueSumByCategorizedAccountPlan(
											@Param("id_conta") Long idContaOrigem,
											@Param("start_date") LocalDate data_start, 
											@Param("end_date") LocalDate data_end);
	 
		
	
	
//	@Query(nativeQuery = true, value = "select * from Lancamento l where l.conta_origem= :id_conta and data between :start_date and :end_date;")
//	List<Transaction> findLancamentoByContaOrigemBetweenData(@Param("id_conta") Long idContaOrigem, @Param("start_date") LocalDate data_start, @Param("end_date") LocalDate data_end);
//	
//	@Query(nativeQuery = true, value = "select * from Lancamento l where l.conta_destino= :id_conta and data between :start_date and :end_date;")
//	List<Transaction> findLancamentoByContaDestinoBetweenData(@Param("id_conta") Long idContaDestino, @Param("start_date") LocalDate data_start, @Param("end_date") LocalDate data_end);
	
}
