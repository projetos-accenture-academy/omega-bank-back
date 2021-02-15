package com.gama.utils;

/**
 * Classe com métodos de validação de campos
 * @author Kellison
 *
 */
public class Validator {
	
	/**
	 * Verifica se um campo é null (quando é um objeto) ou vazio (se for string)
	 * @param object O objeto (ou variavel primitiva) a ser validado
	 * @return boolean
	 */
	public static boolean isEmptyValue(Object object) {
		if((object == null) || (object instanceof String && ((String) object).isEmpty()))
			return true;
		
		return false;
	}
	

	public static void isEmptyValue(Object object,  String message) throws Exception {
		if((object == null) || (object instanceof String && ((String) object).isEmpty()))
			throw new Exception(message);
		
	}
	
	
	
}
