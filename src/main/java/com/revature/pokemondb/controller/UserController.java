package com.revature.pokemondb.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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
import com.revature.pokemondb.models.BannedUser;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.models.dtos.UserDTO;
import com.revature.pokemondb.services.UserService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(path = "/user")
public class UserController {
	private UserService userService;

	public UserController(UserService userServ) {
		this.userService = userServ;
	}

	@RequestMapping(path = "/", method = RequestMethod.OPTIONS)
	public ResponseEntity<String> optionsRequest() {
		return ResponseEntity
				.ok()
				.allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.PATCH,
						HttpMethod.OPTIONS)
				.build();
	}

	/**
	 * Gets a user by id and returns 404 if not found
	 * 
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

		UserDTO userDTO = new UserDTO(user);
		return ResponseEntity.ok(userDTO);
	}

	/**
	 * Gets a user by id and returns 404 if not found
	 * 
	 * @param json
	 * @return
	 */
	@GetMapping("/")
	@Auth(requiredRole = "admin")
	public ResponseEntity<List<UserDTO>> getAllUsers () {
		List<User> allUsers = userService.getAllUsers();
		
		// Convert all users into UsersDTO
		if (allUsers != null) {
			List<UserDTO> allUsersDTO = new ArrayList<>();
			for (User user : allUsers) {
				allUsersDTO.add(new UserDTO(user));
			}
			return ResponseEntity.ok(allUsersDTO);
		}

		return ResponseEntity.notFound().build();
	}

	/**
	 * @param map
	 * @return ResponseEntity<User>
	 */
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser (@RequestBody User newUser) {
		try {
			newUser = userService.registerUser (newUser);
			UserDTO userDTO = new UserDTO(newUser);
			return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
		} catch (UsernameAlreadyExistsException | EmailAlreadyExistsException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (InvalidInputException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/**
	 * @param user
	 * @return ResponseEntity<User>
	 */
	@PutMapping("/")
	@Auth(requireSelfAction = true)
	public ResponseEntity<UserDTO> updateUserDetails (@RequestBody User user) {
		try {
			User updatedUser = userService.updateUser(user);
			if (updatedUser != null) {
				UserDTO userDTO = new UserDTO(updatedUser);
				return ResponseEntity.status(HttpStatus.OK).body(userDTO);
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (UsernameAlreadyExistsException | EmailAlreadyExistsException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	/**
	 * @param user
	 * @return ResponseEntity<User>
	 */
	@PatchMapping
	@Auth(requireSelfAction = true)
	public ResponseEntity<UserDTO> patchUserDetails (@RequestBody User user) {
		try {
			User updatedUser = userService.updateUser(user);
			if (updatedUser != null) {
				UserDTO userDTO = new UserDTO(updatedUser);
				return ResponseEntity.status(HttpStatus.OK).body(userDTO);
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (UsernameAlreadyExistsException | EmailAlreadyExistsException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	/**
	 * @param user
	 * @return ResponseEntity<User>
	 */
	@DeleteMapping("/")
	@Auth(requiredRole = "admin")
	public ResponseEntity<UserDTO> deleteUser (@RequestBody User user) {
		try {
			User deletedUser = userService.deleteUser(user);
			if (deletedUser != null) {
				UserDTO userDTO = new UserDTO(deletedUser);
				return ResponseEntity.status(HttpStatus.OK).body(userDTO);
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/** 
	 * @param user
	 * @return ResponseEntity<User>
	 */
	@PostMapping("/ban")
	@Auth(requiredRole = "admin")
	public ResponseEntity<UserDTO> banUser (@RequestBody BannedUser banBody) {
		try {
			User bannedUser = userService.banUser(banBody);
			UserDTO userDTO = new UserDTO(bannedUser);
			return ResponseEntity.status(HttpStatus.OK).body(userDTO);
		} catch (RecordNotFoundException | UsernameAlreadyExistsException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/** 
	 * @param user
	 * @return ResponseEntity<User>
	 */
	@PostMapping("/unban/{id}")
	@Auth(requiredRole = "admin")
	public ResponseEntity<UserDTO> unBanUser (@PathVariable String id) {
		try {
			// Is this an id?
			User unbannedUser = userService.unBanUser(Long.valueOf(id));
			if (unbannedUser != null) {
				UserDTO userDTO = new UserDTO(unbannedUser);
				return ResponseEntity.status(HttpStatus.OK).body(userDTO);
			}
		} catch (NumberFormatException | RecordNotFoundException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
