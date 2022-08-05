package com.revature.pokemondb.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

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

import com.revature.pokemondb.auth.Auth;
import com.revature.pokemondb.exceptions.EmailAlreadyExistsException;
import com.revature.pokemondb.exceptions.InvalidInputException;
import com.revature.pokemondb.exceptions.RecordNotFoundException;
import com.revature.pokemondb.exceptions.UsernameAlreadyExistsException;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.models.dtos.UserDTO;
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
	public ResponseEntity<String> optionsRequest () {
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
	public ResponseEntity<UserDTO> getUser (@PathVariable String id) {
		User user;
		try {
			// Is this an id?
			user = userService.getUserById(Long.valueOf(id));
		} catch (NumberFormatException e) {
			// No, it's a name
			try {
				user = userService.getUserByUsername(String.valueOf(id));
			} catch (RecordNotFoundException e1) {
				e1.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		if (user != null) {
			UserDTO userDTO = new UserDTO(user);
			return ResponseEntity.ok(userDTO);
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Gets a user by id and returns 404 if not found
	 * @param json
	 * @return
	 */
    @GetMapping("/")
	@Auth(requiredRole = "admin")
	public ResponseEntity<List<User>> getAllUsers () {
		List<User> allUsers = userService.getAllUsers();

		if (allUsers != null) {
			return ResponseEntity.ok(allUsers);
		}
		return ResponseEntity.notFound().build();
	}
    
	/** 
	 * @param map
	 * @return ResponseEntity<User>
	 */
	@PostMapping("/")
	public ResponseEntity<User> createUser (@RequestBody Map<String, String> map) {
		User newUser = new User(map);
		try {
			newUser = userService.registerUser (newUser);
		} catch (UsernameAlreadyExistsException | EmailAlreadyExistsException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (InvalidInputException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
    
	/** 
	 * @param user
	 * @return ResponseEntity<User>
	 */
	@PutMapping("/")
	@Auth(requiredRole = "admin")
	public ResponseEntity<User> updateUserDetails (@RequestBody User user) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user));
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	/** 
	 * @param user
	 * @return ResponseEntity<User>
	 */
	@PatchMapping("/")
	@Auth(requiredRole = "admin")
	public ResponseEntity<User> patchUserDetails (@RequestBody User user) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user));
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
    
	/** 
	 * @param user
	 * @return ResponseEntity<User>
	 */
	@DeleteMapping("/")
	@Auth(requiredRole = "admin")
	public ResponseEntity<User> deleteUser (@RequestBody User user) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user));
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
