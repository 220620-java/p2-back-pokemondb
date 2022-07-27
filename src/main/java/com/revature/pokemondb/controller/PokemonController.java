package com.revature.pokemondb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.pokemondb.services.PokemonService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(path="/pokemon")
public class PokemonController {
    private PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

	
	@GetMapping("/")
	public ResponseEntity<String> getHelloWorld () {
		return ResponseEntity.ok("Hello World Pokemon");
	}

	/**
	 * Get a pokemon from the pokemon service and return it as a string
	 * @param pokemonName
	 * @return
	 */
	@GetMapping(path = "/{pokemonName}")
	public ResponseEntity<String> getPokemonByName(@PathVariable String pokemonName) {
		String pokemon = pokemonService.createPokemon(pokemonName).toString();

		if (pokemon != null) {
			// OK sets status code to 200
			return ResponseEntity.ok(pokemon);
		} else {
			// notFound sets status code to 404
			return ResponseEntity.notFound().build();
		}
	}
}
