package com.revature.pokemondb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.services.PokemonServiceImpl;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(path="/pokemon")
public class PokemonController {
    private PokemonServiceImpl pokemonService;
	private ObjectMapper objectMapper;

    public PokemonController(PokemonServiceImpl pokemonService, ObjectMapper objectMapper) {
        this.pokemonService = pokemonService;
        this.objectMapper = objectMapper;
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
	@GetMapping(
		path = "/{pokemonName}", 
		produces="application/json"
	)
	public ResponseEntity<String> getPokemonByNameOrID(@PathVariable String pokemonName) {
		// Create pokemon object
		Pokemon pokemon;
		try {
			// Is this an id?
			pokemon = pokemonService.createPokemon(Integer.valueOf(pokemonName));
		} catch (NumberFormatException e) {
			// No, it's a name
			pokemon = pokemonService.createPokemon(pokemonName);
		}

		String pokemonJSON;
		try {
			// Turn pokemon into JSON
			pokemonJSON = objectMapper.writeValueAsString(pokemon);
			if (pokemon != null) {
				// OK sets status code to 200
				return ResponseEntity.ok(pokemonJSON);
			} else {
				// notFound sets status code to 404
				return ResponseEntity.notFound().build();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping(
		path = "/poke/{pokemonName}", 
		produces="application/json"
	)
	public ResponseEntity<String> getPokemonByNameOrIDShort(@PathVariable String pokemonName) {
		// Create pokemon object
		Pokemon pokemon;
		try {
			// Is this an id?
			pokemon = pokemonService.getReferencePokemon(Integer.valueOf(pokemonName));
		} catch (NumberFormatException e) {
			// No, it's a name
			pokemon = pokemonService.getReferencePokemon(pokemonName);
		}

		String pokemonJSON;
		try {
			// Turn pokemon into JSON
			pokemonJSON = objectMapper.writeValueAsString(pokemon);
			if (pokemon != null) {
				// OK sets status code to 200
				return ResponseEntity.ok(pokemonJSON);
			} else {
				// notFound sets status code to 404
				return ResponseEntity.notFound().build();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ResponseEntity.notFound().build();
	}
}
