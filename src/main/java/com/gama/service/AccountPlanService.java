package com.gama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.gama.exceptions.AccountPlanAlreadyExistsException;
import com.gama.model.AccountPlan;
import com.gama.model.User;
import com.gama.repository.AccountPlanRepository;
import com.gama.utils.Validator;

public class AccountPlanService {
	
	@Autowired
	private AccountPlanRepository accPlanRepo;
	
	/**
	 * Insere um novo Plano de Conta na base de dados, validando se já existe um com a mesma descrição e usuário
	 * antes de realizar a inserção.
	 * @param accountPlan
	 * @return
	 * @throws IllegalArgumentException
	 * @throws AccountPlanAlreadyExistsException
	 * @throws Exception
	 */
	
	public AccountPlan saveAccountPlan(AccountPlan accountPlan) throws IllegalArgumentException, AccountPlanAlreadyExistsException, Exception
	{
		if(accountPlan !=null)
		{
			//Validates passed description
			Validator.isEmptyValue(accountPlan.getdescription(), "O Plano de conta necessita de uma descrição");
			
			boolean accountPlanExists = accPlanRepo.existsByUserAndDescription(accountPlan.getuser(), accountPlan.getdescription() );
			if(accountPlanExists && accountPlan.getId()==null)
			{
				throw new AccountPlanAlreadyExistsException("Não é possível adicionar o Plano de Conta '" + accountPlan.getdescription() + 
							"' ao usuário " + accountPlan.getuser().getNome() + ". Já existe um registro associado a este usuário.");
			}
			else
			{
				//Adds or updates the given account plan
				return accPlanRepo.save(accountPlan);
			}
		}
		else
		{
			throw new IllegalArgumentException("O Plano de Conta não pode ser nulo");
		}
		
	}
	
	/**
	 * Busca todos os Planos de Conta pertencentes a um usuário
	 * @param user
	 * @return Lista de Planos de Conta de um Usuário
	 * @throws Exception
	 */
	public List<AccountPlan> getAccountPlansByUser(User user) throws Exception
	{
		Validator.isEmptyValue(user, "Não é possível obter uma lista de planos de conta: referência nula de usuário.");
		return accPlanRepo.findByUser(user);
	}
	
	/**
	 * Retorna um Plano de Conta a partir de um ID
	 * @param id
	 * @return Plano de Conta
	 * @throws Exception
	 */
	public AccountPlan getAccountPlanById(Integer id) throws Exception
	{
		Validator.isEmptyValue(id, "Não é possível obter um Plano de Conta a partir de um id nulo.");
		return accPlanRepo.findById(id).get();
	}
	
	
	public void removeAccountPlan(AccountPlan accountPlan) throws Exception
	{
		Validator.isEmptyValue(accountPlan, "Não é possível remover um plano de conta através de uma referência nula");
		
		accPlanRepo.delete(accountPlan);
		
		
	}
	
}
