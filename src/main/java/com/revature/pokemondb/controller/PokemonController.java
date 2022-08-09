package com.revature.pokemondb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.pokemondb.auth.Auth;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.models.dtos.PokemonDTO;
import com.revature.pokemondb.services.PokemonService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(path="/pokemon")
public class PokemonController {
    private PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

	@RequestMapping(path="/", method=RequestMethod.OPTIONS)
	public ResponseEntity<String> optionsRequest () {
		return ResponseEntity
          .ok()
          .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS)
              .build();
	}
	
	@GetMapping("/")
	@Auth
	public ResponseEntity<String> getHelloWorld () {
		return ResponseEntity.ok("Hello World Pokemon");
	}

	@PostMapping("/")
	public ResponseEntity<List<PokemonDTO>> getAllPokemonById (@RequestBody List<Integer> body) {
		List<PokemonDTO> pokemonList = pokemonService.getAllPokemonById(body);
		if (pokemonList != null) {
			return ResponseEntity.status(HttpStatus.OK).body(pokemonList);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@Auth(requiredRole = "admin")
	@GetMapping("/allPokemon")
	public ResponseEntity<List<PokemonDTO>> saveAllPokemonById () {
		List<PokemonDTO> pokemonList = new ArrayList<>();
		try {
			for (int i = 1; i <= 898; i++) {
				PokemonDTO pokemon = pokemonService.createReferencePokemon(i);
				pokemonList.add(pokemon);
			}
			return ResponseEntity.status(HttpStatus.OK).body(pokemonList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/**
	 * Get a pokemon from the pokemon service and return it as a string
	 * @param pokemonName
	 * @return
	 */
	@GetMapping("/{pokemonName}")
	public ResponseEntity<Pokemon> getPokemonByNameOrID(@PathVariable String pokemonName) {
		// Create pokemon object
		Pokemon pokemon;
		try {
			// Is this an id?
			pokemon = pokemonService.createPokemon(Integer.valueOf(pokemonName));
		} catch (NumberFormatException e) {
			// No, it's a name
			pokemon = pokemonService.createPokemon(pokemonName);
		}

		if (pokemon != null) {
			// OK sets status code to 200
			return ResponseEntity.ok(pokemon);
		}

		// notFound sets status code to 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@GetMapping("/poke/{pokemonName}")
	public ResponseEntity<PokemonDTO> getPokemonByNameOrIDShort(@PathVariable String pokemonName) {
		// Create pokemon object
		PokemonDTO pokemon;
		try {
			// Is this an id?
			pokemon = pokemonService.getReferencePokemon(Integer.valueOf(pokemonName));
		} catch (NumberFormatException e) {
			// No, it's a name
			pokemon = pokemonService.getReferencePokemon(pokemonName);
		}
		if (pokemon != null) {
			// OK sets status code to 200
			return ResponseEntity.ok(pokemon);
		}
		// notFound sets status code to 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
