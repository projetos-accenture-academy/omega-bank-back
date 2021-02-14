package com.gama.utils;

/**
 * Classe com métodos de validação de campos
 * @author Kellison
 *
 */
public class Validator {
	
	/**
	 * Verifica se um campo é null (quando é um objeto) ou vazio (se for string)
	 * @param obj O objeto (ou variavel primitiva) a ser validado
	 * @return boolean
	 */
	public static boolean valorVazioOuNull(Object obj) {
		if((obj == null) || (obj instanceof String && ((String) obj).isEmpty()))
			return true;
		
		return false;
	}
	

	public static void valorVazioOuNull(Object obj,  String mensagem) throws Exception {
		if((obj == null) || (obj instanceof String && ((String) obj).isEmpty()))
			throw new Exception(mensagem);
		
	}
	
	
	
}
