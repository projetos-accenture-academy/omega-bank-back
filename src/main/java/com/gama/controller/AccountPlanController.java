package com.gama.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gama.exceptions.AccountPlanAlreadyExistsException;
import com.gama.model.AccountPlan;
import com.gama.model.User;
import com.gama.model.dto.AccountPlanDTO;
import com.gama.repository.AccountPlanRepository;
import com.gama.service.AccountPlanService;

@RestController
@RequestMapping(path="/plan")
public class AccountPlanController {
	
	@Autowired AccountPlanRepository apRepo;
	
	@Autowired
	AccountPlanService apService;
	
//	/**
//	 * Retorna todos os Planos de Conta existentes
//	 * @param 
//	 * @return Uma lista de todos os Planos de Conta existentes
//	 */
//	@GetMapping("/all")
//	public List<AccountPlan> getAllAccountPlans() {
//
//		return apService.get
//		return apRepo.findAll();
//
//	}
	
	/**
	 * Retorna uma lista com todos os planos de conta de um dado usuário
	 * @param user Usuário dono dos Planos de Conta
	 * @return
	 * @throws Exception 
	 */
	@GetMapping("/{user}")
	public List<AccountPlan> getUsersAccountPlans(@PathVariable User user) throws Exception
	{
		return apService.getAccountPlansByUser(user);
	}
	
	
	
	/**
	 * Retorna um Plano de Conta a partir de seu ID (se o mesmo existir)
	 * @param id
	 * @return Um Plano de Conta com o ID dado, se este existir
	 * @throws Exception 
	 */
	@GetMapping("/{id}")
	public AccountPlan getAccountPlan(@PathVariable Integer id) throws Exception
	{
		return apService.getAccountPlanById(id);
	}
	
	/**
	 * Adiciona um Plano de Conta de um usuário específico na base
	 * @param accountPlanDTO Um DTO de entrada de Plano de Conta
	 * @throws Exception 
	 * @throws AccountPlanAlreadyExistsException 
	 * @throws IllegalArgumentException 
	 */
	@PostMapping()
	public ResponseEntity<AccountPlanDTO> addAccountPlan(@RequestBody AccountPlanDTO accountPlanDTO) throws IllegalArgumentException, AccountPlanAlreadyExistsException, Exception
	{
		AccountPlan ap = AccountPlanDTO.transformToObject(accountPlanDTO);
		apService.saveAccountPlan(ap);
		return new ResponseEntity<>(AccountPlanDTO.transformToDTO(ap), HttpStatus.CREATED);
	}
	

	// no PUT for AccountPlan (for initial version, at least)
//	/**
//	 * Altera a descrição de um plano de conta
//	 * @param accountPlanDTO
//	 * @return
//	 * @throws Exception 
//	 * @throws AccountPlanAlreadyExistsException 
//	 * @throws IllegalArgumentException 
//	 */
//	@PutMapping()
//	public ResponseEntity<AccountPlanDTO> alterAccountPlan(@RequestBody AccountPlanDTO accountPlanDTO) throws IllegalArgumentException, AccountPlanAlreadyExistsException, Exception
//	{
//		AccountPlan ap = AccountPlanDTO.transformToObject(accountPlanDTO);
//		apService.saveAccountPlan(ap);
//		return new ResponseEntity<>(AccountPlanDTO.transformToDTO(ap), HttpStatus.CREATED);
//	}
	
	
	
	//No DELETE for AccountPlan
	
	
	
	

}
