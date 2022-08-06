package com.revature.pokemondb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.RateArt;
import com.revature.pokemondb.services.RateArtService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(path = "/rateart")
public class RateArtController {
	private RateArtService rateArtService;
	private ObjectMapper objectMapper;

	public RateArtController(RateArtService rateArtService, ObjectMapper objectMapper) {
		this.rateArtService = rateArtService;
		this.objectMapper = objectMapper;
	}

	/**
	 * Get rating of a fanart as posted by a given user
	 * 
	 * @param artId  the fanart that is rated
	 * @param userId the user associated with the rating
	 * @return a string representing a RateArt object or 404 if rating is not found
	 */
	@GetMapping
	public ResponseEntity<String> getFanartRating(@RequestParam int artId, @RequestParam int userId) {
		// Create fanart object
		RateArt rateArt = rateArtService.getRatingByUserAndFanartId(artId, userId);
		String artCommJSON;
		try {
			// Turn fanart into JSON
			artCommJSON = objectMapper.writeValueAsString(rateArt);
			// OK sets status code to 200
			return ResponseEntity.ok(artCommJSON);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Saves a given RateArt object to the database
	 * 
	 * @param rateArt the fanart rating to be saved
	 * @return a response with a status code to reflect the operation's success
	 */
	@PostMapping(path = "/")
	public ResponseEntity<String> postFanartRating(@RequestBody RateArt rateArt) {
		Boolean success = true;
		RateArt existsTest = rateArtService.getRatingByUserAndFanartId(rateArt.getFanartId().getId(),
				rateArt.getAuthor().getId());
		// Testing for existence of rating
		if (existsTest != null) { // Rating exists. Set ID of new entry to match
			rateArt.setId(existsTest.getId());
		}

		// Save rateArt
		success = rateArtService.saveRating(rateArt);
		if (success) {
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
	}

}
