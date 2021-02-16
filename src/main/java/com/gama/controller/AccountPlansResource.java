package com.gama.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gama.model.AccountPlan;
import com.gama.repository.AccountPlanRepository;

@RestController
@RequestMapping(path="/plans")
public class AccountPlansResource {
	
	@Autowired AccountPlanRepository apRepo;
	
	@GetMapping("/all")
	public List<AccountPlan> getAllAccountPlans() {

		return apRepo.findAll();

	}

}
