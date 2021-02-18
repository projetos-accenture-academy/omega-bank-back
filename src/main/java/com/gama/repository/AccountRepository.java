package com.gama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gama.enums.AccountType;
import com.gama.model.Account;
import com.gama.model.User;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
	interface AccountTypesInterface{
	    AccountType getTipo();
	}
	
	boolean existsByNumero(String number);
	boolean existsByNumeroAndTipo(String number, AccountType type);

	List<Account> findByNumero(String number);
	Account findByNumeroAndTipo(String number, String type);
	
	List<AccountTypesInterface> findTypesByUsuarioAndNumero(User usuario, String numero);	
	List<Account> findByUsuarioId(int id);
	List<Account> findByUsuarioCpf(String cpf);
	List<Account> findByUsuario(User user);
}
