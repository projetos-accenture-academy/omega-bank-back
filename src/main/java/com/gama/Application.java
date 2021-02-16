package com.gama;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gama.model.Account;
import com.gama.model.AccountPlan;
import com.gama.model.User;
import com.gama.repository.AccountPlanRepository;
import com.gama.repository.AccountRepository;
import com.gama.repository.UsuarioRepository;
import com.gama.service.AccountPlanService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	UsuarioRepository usr;
	
	@Autowired
	AccountPlanRepository accplan;
	
	@Bean
	public CommandLineRunner run(AccountRepository bean) throws Exception {
		return args -> {
			System.out.println("\nIniciando o sistema...");
			User u = new User("login", "123456", "Usuario", "00000300399");
			usr.save(u);
			Account account = new Account(u, "login");

			bean.save(account);
			System.out.println("Id é " + account.getId());
			
			System.out.println("Testando Plano de Conta");
			//Inserindo no usuário criado anteriormente
			AccountPlan ap = new AccountPlan(u, "Plano de conta do usuário " + u.getId());
			accplan.save(ap);
//			
//			//Criando um de um user inexistente
//			Usuario u2 = new Usuario("login", "123456", "Usuario", "000.003.003-99"); u2.setId(99);
//			AccountPlan ap2 = new AccountPlan(u2, "Plano de conta do usuário " + u2.getId());
//			accplan.save(ap2);
		};
	}

}
