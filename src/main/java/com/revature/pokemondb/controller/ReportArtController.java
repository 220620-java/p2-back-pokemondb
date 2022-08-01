package com.revature.pokemondb.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.RateArt;
import com.revature.pokemondb.models.ReportArt;
import com.revature.pokemondb.services.ReportArtService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(path="/reportart")
public class ReportArtController {
    private ReportArtService reportArtService;
	private ObjectMapper objectMapper;

    public ReportArtController(ReportArtService reportArtService, ObjectMapper objectMapper) {
        this.reportArtService = reportArtService;
        this.objectMapper = objectMapper;
    }

	/**
	 * Get rating of a fanart as posted by a given user
	 * @param artId the fanart that is Reportd
	 * @param userId the user associated with the rating
	 * @return a string representing a ReportArt object or 404 if rating is not found
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<String> getFanartRating(@PathVariable int artId, @RequestBody int userId) {
		// Create fanart object
		ReportArt ReportArt = reportArtService.getRatingByUserAndFanartId(artId, userId);
		String artCommJSON;
		try {
			// Turn fanart into JSON
			artCommJSON = objectMapper.writeValueAsString(ReportArt);
			if (ReportArt != null) {
				// OK sets status code to 200
				return ResponseEntity.ok(artCommJSON);
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
	 * Saves a given ReportArt object to the database
	 * @param reportArt the fanart rating to be saved
	 * @return a response with a status code to reflect the operation's success
	 */
	@PostMapping(path="/")
	public ResponseEntity<String> postFanartRating(@RequestBody ReportArt reportArt) {
		Boolean success = true;
		ReportArt existsTest = 
				reportArtService.getRatingByUserAndFanartId(reportArt.getFanartId().getId(), reportArt.getAuthor().getId());
		//Testing for existence of rating
		if(existsTest != null) { //Rating exists. Set ID of new entry to match
			reportArt.setId(existsTest.getId());
		}
		
		//Save rateArt
		success = reportArtService.saveRating(reportArt);
		if (success) {
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
	}
	
}
