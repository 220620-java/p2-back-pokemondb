package com.revature.pokemondb;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PokemondbApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PokemondbApplication.class, args);

		Scanner keyboard = new Scanner(System.in);
		printLogo ();
		System.out.println("Welcome to the PokePost Spring Application!");
		System.out.print("Press Enter to stop the application: ");
		keyboard.nextLine();
		keyboard.close();
		System.out.println("Exiting the PokePost Spring Application");
		System.exit(0);
	}

	// This does some cors stuff maybe I think lol
	@Bean
	public WebMvcConfigurer corsConfig() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
					.allowedOrigins("*")
					.allowedHeaders("*")
					.exposedHeaders("*")
					.allowCredentials(false)
					.maxAge(3600);
			}
		};
	}
	
	public static void printLogo () {
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader("src\\main\\java\\com\\revature\\pokemondb\\pokepost_logo.txt"));
			for (String line; (line = reader.readLine()) != null;) {
				System.out.println(line); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) try { reader.close(); } catch (IOException ignore) {}
		}
	}
}
