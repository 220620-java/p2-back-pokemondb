package com.revature.pokemondb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PokemondbApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemondbApplication.class, args);
		testCall();
	}

	public static void testCall () {
		System.out.println("Hello World");
	}

}
