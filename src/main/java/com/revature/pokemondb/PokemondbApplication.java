package com.revature.pokemondb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;

import com.revature.pokemondb.services.PokemonService;

@SpringBootApplication
public class PokemondbApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemondbApplication.class, args);
		testCall();
		RestTemplateBuilder restBuilder = new RestTemplateBuilder();
		PokemonService pokeService = new PokemonService(restBuilder);
		pokeService.getPokemon("pikachu");
	}

	public static void testCall () {
		System.out.println("Hello World");
	}

}
