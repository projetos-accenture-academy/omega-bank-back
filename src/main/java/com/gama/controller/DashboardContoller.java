package com.gama.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gama.model.Account;
import com.gama.model.User;
import com.gama.model.dto.AccountDTO;
import com.gama.model.dto.DashboardDTO;
import com.gama.model.dto.TransactionDTO;
import com.gama.service.AccountService;
import com.gama.service.TransactionService;
import com.gama.service.UserService;

@Controller
@RestController
@RequestMapping(path = "/dashboard")
public class DashboardContoller {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;
	

	@CrossOrigin
	@GetMapping(produces="application/json")
	public Object getDashboard(@RequestParam("login") String login, 
			@RequestParam("dataInicial") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateInitial, 
			@RequestParam("dataFinal") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateEnd) throws Exception
	{

		try {
			if(dateInitial.isAfter(dateEnd))
				return new ResponseEntity<>("A data de fim não pode ser menor que a de início", HttpStatus.ACCEPTED);

			User user = userService.findUserByLogin(login);
			
			DashboardDTO dash = new DashboardDTO();

			List<AccountDTO> accounts = new ArrayList<AccountDTO>();
			
			for (Account account : accountService.getAccountsByUser(user)) {

				List<TransactionDTO> trsDtoS =  (List<TransactionDTO>) transactionService
						.getTransactionsByDestinationAccountAndDateBetween(account.getNumero(), account.getTipo().toString(), dateInitial, dateEnd);

				List<TransactionDTO> trsDtoD =  (List<TransactionDTO>) transactionService
						.getTransactionsBySourceAccountAndDateBetween(account.getNumero(), account.getTipo().toString(), dateInitial, dateEnd);

				List<TransactionDTO> transactions = new ArrayList<TransactionDTO>();
				transactions.addAll(trsDtoD);
				transactions.addAll(trsDtoS);
				
				AccountDTO accDto = DashboardDTO.transformToAccountDTO(account, transactions);
			
				accounts.add(accDto);
			}
			
			dash.setNumberOfAccounts(accounts.size());		
			dash.setAccounts(accounts);
			
			//return dash;
			return new ResponseEntity<>(dash, HttpStatus.OK);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
