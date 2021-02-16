package com.gama.repository;

import org.springframework.stereotype.Repository;

import com.gama.model.AccountPlan;
import com.gama.model.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AccountPlanRepository extends JpaRepository<AccountPlan, Integer> {

	boolean existsById(Integer id);
	boolean existsByDescription(String description);
	boolean existsByUser(Usuario user);
	boolean existsByUserAndDescription(Usuario user, String description);
	
	Optional<AccountPlan> findById(Integer id);
	AccountPlan findByUserAndDescription(Usuario user, String description);
	List<AccountPlan> findByUser(Usuario user);
	
}
