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
import com.revature.pokemondb.models.ReportArtComm;
import com.revature.pokemondb.services.ReportArtCommService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(path = "/reportartcomm")
public class ReportArtCommController {
	private ReportArtCommService reportArtCommService;
	private ObjectMapper objectMapper;

	public ReportArtCommController(ReportArtCommService reportArtCommService, ObjectMapper objectMapper) {
		this.reportArtCommService = reportArtCommService;
		this.objectMapper = objectMapper;
	}

	/**
	 * Get report of a comment as posted by a given user
	 * 
	 * @param commId the comment that is reported
	 * @param userId the user associated with the rating
	 * @return a string representing a ReportArt object or 404 if rating is not
	 *         found
	 */
	@GetMapping
	public ResponseEntity<String> getCommentReported(@RequestParam int commId, @RequestParam int userId) {
		// Create fanart object
		ReportArtComm reportArtComm = reportArtCommService.getRatingByUserAndCommentId(commId, userId);
		String reportArtCommJSON;
		try {
			// Turn fanart into JSON
			reportArtCommJSON = objectMapper.writeValueAsString(reportArtComm);
			// OK sets status code to 200
			return ResponseEntity.ok(reportArtCommJSON);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Saves a given ReportArt object to the database
	 * 
	 * @param reportArt the fanart rating to be saved
	 * @return a response with a status code to reflect the operation's success
	 */
	@PostMapping(path = "/")
	public ResponseEntity<String> postFanartRating(@RequestBody ReportArtComm reportArt) {
		Boolean success = true;
		ReportArtComm existsTest = reportArtCommService.getRatingByUserAndCommentId(reportArt.getCommentId().getId(),
				reportArt.getAuthor().getId());
		// Testing for existence of rating
		if (existsTest != null) { // Rating exists. Set ID of new entry to match
			reportArt.setId(existsTest.getId());
		}

		// Save rateArt
		success = reportArtCommService.saveReport(reportArt);
		if (success) {
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
	}

}
