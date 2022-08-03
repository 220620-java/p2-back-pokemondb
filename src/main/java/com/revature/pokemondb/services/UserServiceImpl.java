package com.revature.pokemondb.services;

import java.util.List;
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
	public User getUserById (Long id) {
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
		Optional<User> user = userRepo.findByUsername(username);
		if (user.isPresent()) { return user.get(); }
		else { return null;}
	}
	
	/**
	 * A user is retrieved from the database if it matches the
	 * username parameter. If password matches then the user is
	 * returned. If either of these fails, null is returned.
	 * A token should be generated for authentication.
	 */
	public User login(String username, String password) {
		Optional<User> oUser = userRepo.findByUsername(username);
		if (oUser.isPresent()) {
			User user = oUser.get();
			String dbPass = user.getPassword();

			// Password is correct
			if (password.equals(dbPass)) {
				return user;
			}
		}
		return null;
	}
	
	/**
	 * Inserts the user into the database. Throws an exception if username already exists.
	 */
	public User registerUser(User user) throws UsernameAlreadyExistsException {
		if (userRepo.existsUserByUsername(user.getUsername())) {
			throw new UsernameAlreadyExistsException();
		}
		return userRepo.save(user);
	}

	/**
	 * Returns all users from the database.
	 * @return
	 */
	public List<User> getAllUsers() {
		return userRepo.findAll();
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
