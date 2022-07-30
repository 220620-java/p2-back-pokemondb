package com.revature.pokemondb.controller;

import com.revature.pokemondb.repositories.FanartRepository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.Fanart;
import com.revature.pokemondb.services.PokemonServiceImpl;
import com.revature.pokemondb.services.FanartService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(path="/fanart")
public class FanartController {
    private PokemonServiceImpl pokemonService;
    private FanartService fanartService;
	private ObjectMapper objectMapper;

    public FanartController(FanartService fanartService, ObjectMapper objectMapper) {
        this.fanartService = fanartService;
        this.objectMapper = objectMapper;
    }

    /**
	 * Get details for all available fanart
	 * @param id
	 * @return a string representing a list of fanart objects
	 */
	@GetMapping("/")
	public ResponseEntity<String> getAllFanart () {
		// Create fanart object
		List<Fanart> fanart = fanartService.getAvailableFanart();
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

	/**
	 * Get a specific fanart's details and associated comments
	 * @param id
	 * @return a string representing a fanart object
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<String> getFanartById(@PathVariable long id) {
		// Create fanart object
		Fanart fanart = fanartService.getFanart(id);
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
	
	/**
	 * Get the lowest and highest available fanart ids and return them as a single string
	 * Returned in format of "{idLow}/{idHigh}"
	 * @return
	 */
	@GetMapping(path= "/info/")
	public ResponseEntity<String> getIdLimiters() {
		String idLimiters = "";
		idLimiters += fanartService.getLowestID();
		idLimiters += "/";
		idLimiters += fanartService.getHighestID();
		if (idLimiters.contains("-1")) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(idLimiters);
		}
	}
}
