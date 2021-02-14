package com.gama;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/*@Bean
	public CommandLineRunner run(PlanoContaRepository bean) throws Exception {
		return args -> {
			System.out.println("\nIniciando o sistema...");
			PlanoConta pc = new PlanoConta();
			pc.setNome("INTERNET");
			bean.save(pc);
			System.out.println("Id é " + pc.getId());
		};
	}*/

}
