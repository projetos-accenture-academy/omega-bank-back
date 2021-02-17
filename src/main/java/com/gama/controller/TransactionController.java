package com.gama.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gama.enums.TransactionType;
import com.gama.model.Transaction;
import com.gama.model.dto.TransactionDTO;
import com.gama.service.TransactionService;


@Controller
@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private ModelMapper modelMapper = new ModelMapper();
	
	@PostMapping(produces="application/json", consumes="application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String novaTransacao(@RequestBody TransactionDTO transactionDTO, TransactionType type) {
		try {
			transactionService.addTransaction(modelMapper.map(transactionDTO, Transaction.class), type);
			return "Transação realizada com sucesso!";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
