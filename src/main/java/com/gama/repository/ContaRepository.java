package com.gama.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gama.model.Conta;
import com.gama.model.Usuario;

@Repository
public interface ContaRepository extends CrudRepository<Conta, Integer> {

	boolean existsByNumero(String numero);
	Conta findByNumero(String numero);
	List<Conta> findByUsuario(Usuario usuario);
}
