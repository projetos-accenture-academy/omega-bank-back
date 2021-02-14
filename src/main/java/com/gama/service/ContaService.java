package com.gama.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gama.exceptions.ContaExistenteException;
import com.gama.model.Conta;
import com.gama.model.Usuario;
import com.gama.repository.ContaRepository;
import com.gama.utils.Validator;

@Component
public class ContaService{
	@Autowired
	private ContaRepository contaRepository;

	/**
	 * 
	 * Realiza a inserção de uma conta, validando se já existe ou não uma conta
	 * com o mesmo número
	 * 
	 * @param conta Conta a ser inserida
	 * @param conta
	 * @return
	 * @throws Exception 
	 */
	public Conta adicionarConta(Conta conta) throws Exception{
		if(conta != null) {
			/*
			 * Realiza validações quanto ao valor do atributo usuario da conta
			 */
			Validator.valorVazioOuNull(conta.getTipo(), "A conta necessita de um tipo definido");
			Validator.valorVazioOuNull(conta.getUsuario(), "A conta necessita de um usuário associado");
			Validator.valorVazioOuNull(conta.getUsuario().getLogin(), "A conta necessita de um usuário com login");
			
			boolean contaExistente = contaRepository.existsByNumero(conta.getNumero());
	
			if (!contaExistente)
				return contaRepository.save(conta);
			else
				throw new ContaExistenteException("Conta Nº " + conta.getNumero() + " já existe.");
			
		} else {
			throw new IllegalArgumentException("A conta não pode ser null");
		}
	}

	/**
	 * Busca as contas pertecentes à um usuário específico
	 * @param usuario
	 * @return List<Conta>
	 * @throws Exception  Se o parâmetro de busca "usuario" for null
	 */
	public List<Conta> getContasPorUsuario(Usuario usuario) throws Exception {
		Validator.valorVazioOuNull(usuario, "Não é possível obter uma lista de contas através de uma referência nula de usuário");
		return contaRepository.findByUsuario(usuario);
	}
	
	/**
	 * 
	 * @param numero Número da conta ser pesquisada
	 * @return Conta
	 * @throws Exception 
	 */
	public Conta getContaPorNumero(String numero) throws Exception {
		Validator.valorVazioOuNull(numero, "Não é possível pesquisar uma conta através de uma parâmetro nulo");

		return contaRepository.findByNumero(numero);
	}

	/**
	 * 
	 * @param conta
	 * @throws Exception Se o parâmetro de conta para remoção for null
	 */
	public void removerConta(Conta conta) throws Exception {	
		Validator.valorVazioOuNull(conta, "Não é possível remover uma conta através de uma referência nula");

		contaRepository.delete(conta);
	}
	
}
