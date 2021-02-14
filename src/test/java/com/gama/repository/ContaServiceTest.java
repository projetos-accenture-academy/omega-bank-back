package com.gama.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gama.exceptions.ContaExistenteException;
import com.gama.model.Conta;
import com.gama.model.Usuario;
import com.gama.service.ContaService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.AUTO_CONFIGURED)
@TestMethodOrder(OrderAnnotation.class)
public class ContaServiceTest {

    @Autowired
    ContaService ContaService;
   
    @Autowired
    UsuarioRepository usuarioRepository;

	static Usuario usuarioTeste;
	
	@BeforeAll
	static void init() {
		System.out.println("Inicializando objetos de testes: ContaService");
		usuarioTeste = new Usuario("aabc", "123456", "A", "00100200399");
	}
	
	@BeforeEach
	void executandoTests(TestInfo testInfo) {
		System.out.println("  Executando teste: " + testInfo.getDisplayName());
	}
	

    @Test
    @DisplayName("Inibir a criação de conta sem usuário associado")
    public void criarContaSemUsuario() throws Exception {  
    	
        Exception exception = assertThrows(Exception.class, () -> {
        	ContaService.adicionarConta(new Conta(null, ""));
        });
        
        assertEquals("A conta necessita de um usuário associado", exception.getMessage());
    	    	
        exception = assertThrows(Exception.class, () -> {
        	ContaService.adicionarConta(new Conta(new Usuario(), ""));
        });
        
        assertEquals("A conta necessita de um usuário com login", exception.getMessage());

    }
    

    @Test
    @DisplayName("Inibir a criação de conta sem tipo definido")
    public void criarContaSemTipoDefinido() throws Exception {  
    	Usuario usuario = new Usuario();
    	usuario.setLogin("login");
        Conta conta = new Conta(usuario, "num123");
        
        conta.setTipo(null);
    	
        Exception exception = assertThrows(Exception.class, () -> {
        	ContaService.adicionarConta(conta);
        });
        
        assertEquals("A conta necessita de um tipo definido", exception.getMessage());


    }
    

    @Test
    @DisplayName("Tentativa falha de obter lista de constas sem passar o usuário associado")
    public void obterListaUsuarioComParamNull() throws Exception {

    	Exception exception = assertThrows(Exception.class, () ->{
    		ContaService.getContasPorUsuario(null);
    	});
    	
    	assertEquals("Não é possível obter uma lista de contas através de uma referência nula de usuário", exception.getMessage());
    }
    
    @Test
    @DisplayName("Tentativa falha de pesquisar uma conta sem passar um número válido")
    public void obterContaComParamNull() throws Exception {

    	Exception exception = assertThrows(Exception.class, () ->{
    		ContaService.getContaPorNumero(null);
    	});
    	
    	assertEquals("Não é possível pesquisar uma conta através de uma parâmetro nulo", exception.getMessage());
    }
    
    @Test
    @DisplayName("Inibir a remoção uma conta com valor null")
    public void deletarContaComParamNull() throws Exception {

    	Exception exception = assertThrows(Exception.class, () ->{
    		ContaService.removerConta(null);
    	});
    	
    	assertEquals("Não é possível remover uma conta através de uma referência nula", exception.getMessage());
    }
    

    @Test
    @DisplayName("Obter uma conta sem um número válido")
    public void obterContaSemNumero() throws Exception {    	
    	assertNull(ContaService.getContaPorNumero("vazio"));
    }
    
    
	@Test
    @Order(1)
    @DisplayName("Criar conta para um novo usuário")
    public void criarConta() throws Exception {
        Usuario usuarioCriado = usuarioRepository.save(usuarioTeste);
        
    	assertNotNull(usuarioCriado);
    	
        assertNotNull(ContaService.adicionarConta(new Conta(usuarioCriado, usuarioCriado.getLogin())));
    }
	    
    @Test
    @Order(2)
    @DisplayName("Inibir a criação de conta com número já existente para um usuário")
    public void criarContaExistente() throws Exception {  
    	
    	// Procura pelo usuário cadastrado no teste anterior para tentar adicionar uma nova conta
    	// com o mesmo número para este usuário
    	Optional<Usuario> usuarioCriado = usuarioRepository.findByLogin(usuarioTeste.getLogin());
    	Conta conta = new Conta(usuarioCriado.get(), usuarioCriado.get().getLogin());

    	assertNotNull(usuarioCriado);
    	
        Exception exception = assertThrows(ContaExistenteException.class, () -> {
        	ContaService.adicionarConta(conta);
        });

        
        String mensagemEsperada = "Conta Nº " + conta.getNumero() + " já existe.";
        String mensagemRecebida = exception.getMessage();
        
        assertTrue(mensagemRecebida.equals(mensagemEsperada));

    }
    
    @Test
    @Order(3)
    @DisplayName("Obter uma conta através do seu número")
    public void obterConta() throws Exception {
    	assertNotNull(ContaService.getContaPorNumero(usuarioTeste.getLogin()));
    }
    
    @Test
    @Order(4)
    @DisplayName("Obter todas as contas de um usuário")
    public void obterTodasContasUsuario() throws Exception {
    	List<Conta> todasContas = ContaService.getContasPorUsuario(usuarioTeste);
    	
    	assertNotNull(todasContas);
    	
    	assertEquals(1, todasContas.size());
    }
    
    @Test
    @Order(5)
    @DisplayName("Deletar uma conta de um usuário")
    public void deletarConta() throws Exception {
    	
    	Conta conta = ContaService.getContaPorNumero(usuarioTeste.getLogin());
    	assertNotNull(conta);

    	ContaService.removerConta(conta);
    	
    	assertNull(ContaService.getContaPorNumero(conta.getNumero()));
    	
    }
}