package com.gama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gama.enums.AccountType;
import com.gama.enums.TransactionType;
import com.gama.model.Account;
import com.gama.model.AccountPlan;
import com.gama.model.User;
import com.gama.repository.AccountPlanRepository;
import com.gama.repository.UserRepository;
import com.gama.utils.UserValidator;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountPlanRepository planRepository;
	
		
	@Autowired
	PasswordEncoder encoder;
	
	private UserValidator userValidator;

	public User saveUser(User user) throws Exception {
		
		userValidator = new UserValidator(user);
		
		if (userRepository.existsById(user.getId() | 0)) {
			throw new Exception("Usuário já cadastrado!");
		} 
		
		if (!userValidator.valid()) {
			throw new Exception("Falha ao inserir usuário: " + System.lineSeparator() +	userValidator.getListError());
		} 
		
		if (userRepository.existsByLogin(user.getLogin())) {
			throw new Exception("Nome de login já cadastrado!");
		}
		
		String passCriptografada = encoder.encode(user.getSenha());
		user.setSenha(passCriptografada);
			
		User newUser = userRepository.save(user);
		
		Account accountCC = new Account(newUser, AccountType.CC);
		accountService.saveAccount(accountCC);
		Account accountCB = new Account(newUser, AccountType.CB);
		accountService.saveAccount(accountCB);	
		
		AccountPlan pc = new AccountPlan(newUser, TransactionType.R.getDescription(), TransactionType.R);
		planRepository.save(pc);
		
		pc = new AccountPlan(newUser, TransactionType.D.getDescription(),TransactionType.D);
		planRepository.save(pc);
		
		pc = new AccountPlan(newUser, TransactionType.T.getDescription(), TransactionType.T);
		planRepository.save(pc);
		
		//Não existe outro tipo de Transação
		/*
		pc = new AccountPlan(newUser, TransactionType.TU.getDescription(), TransactionType.TU);
		planRepository.save(pc);
			*/	
		return newUser;		
	}

	public User updateUser(User user) throws Exception {
		userValidator = new UserValidator(user);
		if (!userRepository.existsById(user.getId())) {
			throw new Exception("Usuário não cadastrado!");
		} else if (!userValidator.valid()) {
			throw new Exception("Falha ao alterar usuário: " + System.lineSeparator() +	userValidator.getListError());
		} else {
			return userRepository.save(user);
		}
	}

	public void removeUser(User user) {
		userRepository.delete(user);
	}

	public void removeUserById(int id) {
		userRepository.deleteById(id);
	}

	public Optional<User> findUserById(int id) {
		return userRepository.findById(id);
	}

	public User findUserByLogin(String login) {
		return userRepository.findByLogin(login);
	}

	public List<User> FindAllUsuarios() {
		return userRepository.findAll();
	}

}
