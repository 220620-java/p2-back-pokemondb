package com.revature.pokemondb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.Fanart;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.services.PokemonService;

@RestController
@RequestMapping(path="/fanart")
public class FanartController {
    private PokemonService pokemonService;
	private ObjectMapper objectMapper;

    public FanartController(PokemonService pokemonService, ObjectMapper objectMapper) {
        this.pokemonService = pokemonService;
        this.objectMapper = objectMapper;
    }

    /**
	 * Get urls, titles, and ids for all availible fanart
	 * @param id
	 * @return
	 */
	@GetMapping("/")
	public ResponseEntity<String> getAll () {
		return ResponseEntity.ok(null);
	}

	/**
	 * Get a specific fanart's details and associated comments and return it as a string
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/{id}}")
	public ResponseEntity<String> getFanartById(@PathVariable int id) {
		// Create fanart object
		Fanart fanart = FanartRepository.findById(id);
		String fanartJSON;
		try {
			// Turn fanart into JSON
			fanartJSON = objectMapper.writeValueAsString(fanart);
			if (fanart != null) {
				// OK sets status code to 200
				return ResponseEntity.ok(fanartJSON);
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
