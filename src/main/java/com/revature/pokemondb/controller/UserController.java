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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.pokemondb.exceptions.UsernameAlreadyExistsException;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.services.UserService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(path="/user")
public class UserController {
	private UserService userService;

	public UserController (UserService userServ) {
		this.userService = userServ;
	}

	@RequestMapping(path="/", method=RequestMethod.OPTIONS)
	public ResponseEntity<?> optionsRequest () {
		return ResponseEntity
          .ok()
          .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.PATCH, HttpMethod.OPTIONS)
              .build();
	}

	/**
	 * Gets a user by id and returns 404 if not found
	 * @param json
	 * @return
	 */
    @GetMapping("/{id}")
	public ResponseEntity<User> getUser (@PathVariable Integer id) {
		User user = userService.getUserById(id);
		if (user != null) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.notFound().build();
	}

    @PostMapping("/")
	public ResponseEntity<User> createUser (@RequestBody User user) {
		try {
			user = userService.registerUser (user);
		} catch (UsernameAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
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
