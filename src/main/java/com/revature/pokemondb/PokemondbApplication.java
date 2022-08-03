package com.revature.pokemondb;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
