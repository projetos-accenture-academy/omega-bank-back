package com.gama.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gama.enums.AccountType;
import com.gama.enums.TransactionType;
import com.gama.exceptions.AccountAlreadyExistsException;
import com.gama.exceptions.AccountDoesNotExistsException;
import com.gama.exceptions.BalanceNotEnoughException;
import com.gama.model.Account;
import com.gama.model.User;
import com.gama.repository.AccountRepository;
import com.gama.repository.UserRepository;
import com.gama.utils.Validator;

@Component
public class AccountService{
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * 
	 * Realiza a inserção ou atualização de uma conta, validando se já existe ou não uma conta
	 * com o mesmo número e tipo. Caso exista, a conta é atualizada, caso contrário é adicionada uma nvoa conta.
	 * 
	 * @param account Conta a ser salva
	 * @param account
	 * @return
	 * @throws Exception 
	 */
	public Account saveAccount(Account account) throws IllegalArgumentException, Exception{
		if(account != null) {
			/*
			 * Realiza validações quanto ao valor do atributo usuario da conta
			 */
			Validator.isEmptyValue(account.getTipo(), "A conta necessita de um tipo definido");
			Validator.isEmptyValue(account.getUsuario(), "A conta necessita de um usuário associado");
			Validator.isEmptyValue(account.getUsuario().getLogin(), "A conta necessita de um usuário com login");
			
			boolean contaExistente = accountRepository.existsByNumeroAndTipo(account.getNumero(), account.getTipo());
	
			if (contaExistente && account.getId() == null)
				throw new Exception("Não é possível adicionar a conta Nº " + 
						account.getNumero() + 
						" pois já existe um registro com esse número.");
			else
				return accountRepository.save(account);
			
		} else {
			throw new IllegalArgumentException("A conta não pode ser null");
		}
		
	}
	
	/**
	 * Realiza a atualização do saldo de uma conta. 
	 * Caso o valor passado seja negativo, uma operação de débito é realizada subtraindo o valor do saldo atual;
	 * Se o valor passado for positivo, é feita uma adição ao saldo atual;
	 * 
	 * @param accountID
	 * @param valueToAddOrSub
	 * @throws BalanceNotEnoughException
	 * @throws AccountDoesNotExistsException 
	 */
	public void updateAccountBalance(Integer accountID, Double valueToAddOrSub) throws AccountAlreadyExistsException, 
	BalanceNotEnoughException, Exception{
		Optional<Account> account = accountRepository.findById(accountID);
		
		if(account.get() == null){
			throw new AccountDoesNotExistsException();
		} else {
			Double newBalance = account.get().getSaldo() + valueToAddOrSub;
			
			if(newBalance < 0) {
				throw new BalanceNotEnoughException(valueToAddOrSub);
			}
			else {
				account.get().setSaldo(newBalance);
				saveAccount(account.get());
			}
		}
	}
	
	/**
	 * Busca as contas pertecentes à um usuário específico
	 * @param id
	 * @return List<Conta>
	 * @throws Exception se o usuário não existir
	 * 
	 * @author Alessandra Canuto
	 */
	public List<Account> getAccountsByUserId(int id) throws Exception {
		Validator.isEmptyValue(userRepository.findById(id), "Não é possível obter uma lista de um usuário inexistente.");
		return accountRepository.findByUsuarioId(id);
	}

	/**
	 * Busca as contas pertecentes à um usuário específico
	 * @param usuario
	 * @return List<Conta>
	 * @throws Exception  Se o parâmetro de busca "usuario" for null
	 */
	public List<Account> getAccountsByUser(User usuario) throws Exception {
		Validator.isEmptyValue(usuario, "Não é possível obter uma lista de contas através de uma referência nula de usuário.");
		return accountRepository.findByUsuario(usuario);
	}
	
	/**
	 * Retorna uma conta de um usuário
	 * @param number Número da conta ser pesquisada
	 * @param type Tipo da conta ser pesquisada
	 * @return Conta
	 * @throws Exception 
	 */
	public Account getAccountByNumberAndType(String number, AccountType type) throws Exception {
		Validator.isEmptyValue(number, "Não é possível pesquisar uma conta através de uma parâmetro nulo.");

		return accountRepository.findByNumeroAndTipo(number, type);
	}

	/**
	 * 
	 * @param conta
	 * @throws Exception Se o parâmetro de conta para remoção for null
	 */
	public void removeAccount(Account account) throws Exception {	
		Validator.isEmptyValue(account, "Não é possível remover uma conta através de uma referência nula.");

		accountRepository.delete(account);
	}
	
}
