package com.gama;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gama.model.Usuario;
import com.gama.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.AUTO_CONFIGURED)
@TestMethodOrder(OrderAnnotation.class)
class UsuarioTest {

	private Usuario usuario;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		usuario = new Usuario();
	}

	@Test
	@Order(1)
	void testAddUsuario() {
		usuario.setNome("Alessandra");
		usuario.setCpf("000.000.001-00");
		usuario.setLogin("acanuto");
		usuario.setSenha("123456");
		assertTrue(usuarioRepository.save(usuario) != null);		
	}
	
	@Test
	void testAddUsuarioInvalido() {
		usuario.setNome("Alessandra");
		usuario.setCpf("000.000.002-00");
		usuario.setLogin("acanuto");
		usuario.setSenha("123");
		assertFalse(usuarioRepository.save(usuario) == null, String.join(", ", usuario.getListError()));
	}
	
	@Test
	@Order(2)
	void testAddUsuarioCadastrado() {
		usuario.setNome("Alessandra");
		usuario.setCpf("000.000.001-00");
		usuario.setLogin("acanuto");
		usuario.setSenha("123456");

		assertThrows(Exception.class, () -> {
			
        	usuarioRepository.save(usuario);
        });

				
		assertFalse(usuarioRepository.existsById(usuario.getId()), "teste");
	}

}
