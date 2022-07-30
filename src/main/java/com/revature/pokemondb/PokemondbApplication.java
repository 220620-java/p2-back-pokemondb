package com.revature.pokemondb;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.services.PokemonService;
import com.revature.pokemondb.services.PokemonServiceImpl;

@SpringBootApplication
public class PokemondbApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemondbApplication.class, args);
		ObjectMapper objectMapper = new ObjectMapper();
		PokemonService pokeService = new PokemonServiceImpl(objectMapper);

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

	@Bean
	public WebMvcConfigurer corsConfig() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
					.allowedOrigins("null")
					.allowedHeaders("*")
					.exposedHeaders("Auth")
					.allowCredentials(false);
			}
		};
	}

	public static void testCall () {
		System.out.println("Hello World");
	}



}
