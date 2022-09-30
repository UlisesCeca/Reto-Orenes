package com.orenes.reto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.orenes.reto.dao.OrderDAO;
import com.orenes.reto.repositories.OrderRepository;

@SpringBootApplication
public class RetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetoApplication.class, args);
	}
	
	@Bean
	  public CommandLineRunner demo(OrderRepository repository) {
	    return (args) -> {
	      // save a few customers
	      repository.save(new OrderDAO("Calle Almendros, 5"));
	      System.out.println(repository.findAll());
	    };
	  }

}
