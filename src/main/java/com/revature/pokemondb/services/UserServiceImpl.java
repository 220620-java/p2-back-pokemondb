package com.revature.pokemondb.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.pokemondb.exceptions.UsernameAlreadyExistsException;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepo;

	public UserServiceImpl (UserRepository repo) {
		this.userRepo = repo;
	}

	/**
	 * Retrieves a user by its ID.
	 * @param id
	 * @return an Optional User is returned
	 */
	public User getUserById (int id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) { return user.get();}
		else { return null;}
	}

	/**
	 * Retrieves a user by its username.
	 * @param username
	 * @return a User is returned
	 */
	public User getUserByUsername (String username) {
		User user = userRepo.findByUsername(username);
		return user;
	}
	
	/**
	 * A user is retrieved from the database if it matches the
	 * username parameter. If password matches then the user is
	 * returned. If either of these fails, null is returned.
	 * A token should be generated for authentication.
	 */
	public User login(String username, String password) {
		return null;
	}
	
	/**
	 * Inserts the user into the database.
	 */
	public User registerUser(User user) throws UsernameAlreadyExistsException {
		System.out.println("Registering new user");
		User savedUser = userRepo.save(user);
		return savedUser;
	}
	
	/**
	 * Inserts the user into the ban table to indicate
	 * a user has been banned.
	 */
	public User banUser(User user) {
		return null;
	}
	
	/**
	 * Removes a user from the ban table to indicate
	 * a user has been unbanned.
	 */
	public User unBanUser(User user) {
		return null;
	}
}
