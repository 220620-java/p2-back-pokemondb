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
import com.revature.pokemondb.models.ArtComment;
import com.revature.pokemondb.services.ArtCommService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(path = "/artcomm")
public class ArtCommController {
	private ArtCommService artCommService;
	private ObjectMapper objectMapper;

	public ArtCommController(ArtCommService artCommService, ObjectMapper objectMapper) {
		this.artCommService = artCommService;
		this.objectMapper = objectMapper;
	}

	/**
	 * Get available comments associated with a given fanart
	 * 
	 * @param id
	 * @return a string representing a fanart object
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<String> getCommentsByArtId(@PathVariable int id) {
		// Create fanart object
		List<ArtComment> fanart = artCommService.getAvailibleFanartComments(id);
		String artCommJSON;
		try {
			// Turn fanart into JSON
			artCommJSON = objectMapper.writeValueAsString(fanart);
			if (fanart != null) {
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
	 * Saves a given ArtComment object to the database
	 * 
	 * @param artComm the fanart comment to be saved
	 * @return a response with a status code to reflect the operation's success
	 */
	@PostMapping(path = "/create")
	public ResponseEntity<String> postComment(@RequestBody ArtComment artComm) {
		Boolean success = true;
		success = artCommService.addComment(artComm);
		if (success) {
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
	}
}
