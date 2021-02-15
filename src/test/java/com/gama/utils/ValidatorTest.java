/**
 * 
 */
package com.gama.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.gama.model.Account;

/**
 * @author Kellison
 *
 */
class ValidatorTest {

	@BeforeAll
	static void init() {
		System.out.println("Inicializando testes: Validator");
	}
	
	@BeforeEach
	void executandoTests(TestInfo testInfo) {
		System.out.println("  Executando teste: " + testInfo.getDisplayName());
	}
	
	@Test
	@DisplayName("Validar objeto nulo ou vazio")
	void validarObjetoNuloComBoolean() {
		Account conta = null;

		assertTrue(Validator.isEmptyValue(conta));
		
		String vazia = "";
		assertTrue(Validator.isEmptyValue(vazia));
	}
	
	@Test
	@DisplayName("Validar objeto nulo, retornando uma exception")
	void validarObjetoNuloComException() {

		Exception exception = assertThrows(Exception.class, () -> {
			Validator.isEmptyValue(new Account().getNumero(), "O objeto não pode ser null");
		});
		
		assertEquals("O objeto não pode ser null", exception.getMessage());
		
	}

}
