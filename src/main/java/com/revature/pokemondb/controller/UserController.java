package com.revature.pokemondb.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(path="/user")
public class UserController {
	@RequestMapping(path="/", method=RequestMethod.OPTIONS)
	public ResponseEntity<?> optionsRequest () {
		return ResponseEntity
          .ok()
          .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.PATCH, HttpMethod.OPTIONS)
              .build();
	}

	/**
	 * @param json
	 * @return
	 */
    @GetMapping("/{id}")
	public ResponseEntity<String> getUser (@PathVariable Integer id) {
		return ResponseEntity.ok("Nice");
	}

    @PostMapping("/")
	public ResponseEntity<String> createUser () {
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

    @PutMapping("/")
	public ResponseEntity<String> updateUserDetails () {
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

	@PatchMapping("/")
	public ResponseEntity<String> patchUserDetails () {
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

    @DeleteMapping("/")
	public ResponseEntity<String> deleteUser () {
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}
}
