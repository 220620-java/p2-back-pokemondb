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
import com.revature.pokemondb.models.ReportArtComm;
import com.revature.pokemondb.services.ReportArtCommService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(path="/reportartcomm")
public class ReportArtCommController {
    private ReportArtCommService reportArtCommService;
	private ObjectMapper objectMapper;

    public ReportArtCommController(ReportArtCommService reportArtCommService, ObjectMapper objectMapper) {
        this.reportArtCommService = reportArtCommService;
        this.objectMapper = objectMapper;
    }

	/**
	 * Get rating of a fanart as posted by a given user
	 * @param artId the fanart that is Reportd
	 * @param userId the user associated with the rating
	 * @return a string representing a ReportArt object or 404 if rating is not found
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<String> getFanartRating(@PathVariable int commId, @RequestBody int userId) {
		// Create fanart object
		ReportArtComm reportArtComm = reportArtCommService.getRatingByUserAndCommentId(commId, userId);
		String reportArtCommJSON;
		try {
			// Turn fanart into JSON
			reportArtCommJSON = objectMapper.writeValueAsString(reportArtComm);
			if (reportArtComm != null) {
				// OK sets status code to 200
				return ResponseEntity.ok(reportArtCommJSON);
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
	public ResponseEntity<String> postFanartRating(@RequestBody ReportArtComm reportArt) {
		Boolean success = true;
		ReportArtComm existsTest = 
				reportArtCommService.getRatingByUserAndCommentId(reportArt.getCommentId().getId(), reportArt.getAuthor().getId());
		//Testing for existence of rating
		if(existsTest != null) { //Rating exists. Set ID of new entry to match
			reportArt.setId(existsTest.getId());
		}
		
		//Save rateArt
		success = reportArtCommService.saveReport(reportArt);
		if (success) {
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
	}
	
}
