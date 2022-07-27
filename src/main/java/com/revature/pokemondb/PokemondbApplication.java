package com.revature.pokemondb;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;

import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.services.PokemonService;

@SpringBootApplication
public class PokemondbApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemondbApplication.class, args);
		RestTemplateBuilder restBuilder = new RestTemplateBuilder();
		PokemonService pokeService = new PokemonService(restBuilder);

		// pokeService.printPokemonInformation("pikachu");
		// pokeService.printPokemonInformation("ditto");
		// pokeService.printPokemonInformation("spheal");
		// pokeService.printPokemonInformation("Mr. Mime");
		Scanner keyboard = new Scanner(System.in);
		String input;
		do {
			System.out.print("Enter a Pokemon: ");
			input = keyboard.nextLine();
			if (input != "\n" && input != "" && input != " ") {
				// pokeService.printPokemonInformation(input);
				Pokemon pokemon = pokeService.createPokemon(input);
				System.out.println(pokemon);
			}
		} while (input != "\n" && input != "");
		keyboard.close();
		System.exit(0);
	}

	public static void testCall () {
		System.out.println("Hello World");
	}

}
