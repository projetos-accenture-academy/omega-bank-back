package com.gama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gama.enums.TipoConta;
import com.gama.model.Account;
import com.gama.model.Usuario;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

	boolean existsByNumero(String number);
	boolean existsByNumeroAndTipo(String number, TipoConta type);
	Account findByNumero(String number);
	List<Account> findByUsuarioCpf(String cpf);
	List<Account> findByUsuario(Usuario user);
}
