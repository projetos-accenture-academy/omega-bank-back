package com.gama;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gama.model.Account;
import com.gama.model.Usuario;
import com.gama.repository.AccountRepository;
import com.gama.repository.UsuarioRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	UsuarioRepository usr;
	
	@Bean
	public CommandLineRunner run(AccountRepository bean) throws Exception {
		return args -> {
			System.out.println("\nIniciando o sistema...");
			Usuario u = new Usuario("login", "123456", "Usuario", "000.003.003-99");
			usr.save(u);
			Account pc = new Account(u, "login");

			bean.save(pc);
			System.out.println("Id é " + pc.getId());
		};
	}

}
