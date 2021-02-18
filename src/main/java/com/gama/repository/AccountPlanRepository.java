package com.gama.repository;

import org.springframework.stereotype.Repository;

import com.gama.model.AccountPlan;
import com.gama.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface AccountPlanRepository extends JpaRepository<AccountPlan, Integer> {

	boolean existsById(int id);
	boolean existsByDescription(String description);
	boolean existsByUser(User user);
	boolean existsByUserAndDescription(User user, String description);
	
	Optional<AccountPlan> findById(int id);
	AccountPlan findByUserAndDescription(User user, String description);
	List<AccountPlan> findByUser(User user);
	
	@Query(nativeQuery = true, value = "SELECT * FROM planos_conta WHERE usuario_id = :id")
	List<AccountPlan> findAllByUserId(@Param("id") int id);
	
}
