package com.revature.pokemondb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.RateArtComm;
import com.revature.pokemondb.services.RateArtCommService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(path="/rateartcomm")
public class RateArtCommController {
    private RateArtCommService rateArtCommService;
	private ObjectMapper objectMapper;

    public RateArtCommController(RateArtCommService rateArtCommService, ObjectMapper objectMapper) {
        this.rateArtCommService = rateArtCommService;
        this.objectMapper = objectMapper;
    }

	/**
	 * Get rating of a art comment as posted by a given user
	 * @param commId the comment that is rated
	 * @param userId the user associated with the rating
	 * @return a string representing a RateArt object or 404 if rating is not found
	 */
	@GetMapping
	public ResponseEntity<String> getCommentRating(@RequestParam int commId, @RequestParam int userId) {
		// Create fanart object
		RateArtComm rateArtComm = rateArtCommService.getRatingByUserAndCommentId(commId, userId);
		String artCommJSON;
		try {
			// Turn fanart into JSON
			artCommJSON = objectMapper.writeValueAsString(rateArtComm);
			// OK sets status code to 200
			return ResponseEntity.ok(artCommJSON);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Saves a given RateArt object to the database
	 * @param rateArt the fanart rating to be saved
	 * @return a response with a status code to reflect the operation's success
	 */
	@PostMapping(path="/")
	public ResponseEntity<String> postFanartRating(@RequestBody RateArtComm rateArtComm) {
		Boolean success = true;
		RateArtComm existsTest = 
				rateArtCommService.getRatingByUserAndCommentId(rateArtComm.getCommentId().getId(), rateArtComm.getAuthor().getId());
		//Testing for existence of rating
		if(existsTest != null) { //Rating exists. Set ID of new entry to match
			rateArtComm.setId(existsTest.getId());
		}
		
		//Save rateArt
		success = rateArtCommService.saveRating(rateArtComm);
		if (success) {
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
	}
	
}
