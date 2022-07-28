package com.revature.pokemondb;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.services.PokemonService;

@SpringBootApplication
public class PokemondbApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemondbApplication.class, args);
		RestTemplateBuilder restBuilder = new RestTemplateBuilder();
		PokemonService pokeService = new PokemonService(restBuilder);
		ObjectMapper objectMapper = new ObjectMapper();

		Scanner keyboard = new Scanner(System.in);
		String input;
		do {
			System.out.print("Enter a Pokemon: ");
			input = keyboard.nextLine();
			if (input != "\n" && input != "" && input != " ") {
				// pokeService.printPokemonInformation(input);
				Pokemon pokemon = pokeService.createPokemon(input);
				try {
					// Return object has JSON
					String pokemonJSON = objectMapper.writeValueAsString(pokemon);
					System.out.println(pokemonJSON);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} while (input != "\n" && input != "");
		keyboard.close();
		System.exit(0);
	}

	public static void testCall () {
		System.out.println("Hello World");
	}

}
