package com.revature.pokemondb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.pokemondb.exceptions.FailedAuthenticationException;
import com.revature.pokemondb.exceptions.RecordNotFoundException;
import com.revature.pokemondb.exceptions.UsernameAlreadyExistsException;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.repositories.UserRepository;
import com.revature.pokemondb.utils.WebUtils;

@Service("userService")
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
	public User getUserById (Long id) throws RecordNotFoundException {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) { return user.get();}
		else { throw new RecordNotFoundException();}
	}

	/**
	 * Retrieves a user by its username.
	 * @param username
	 * @return a User is returned
	 */
	public User getUserByUsername (String username) throws RecordNotFoundException {
		Optional<User> user = userRepo.findByUsername(username);
		if (user.isPresent()) { return user.get(); }
		else { throw new RecordNotFoundException();}
	}

	/**
	 * Returns all users from the database.
	 * @return
	 */
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	/**
	 * A user is retrieved from the database if it matches the
	 * username parameter. If password matches then the user is
	 * returned. If either of these fails, null is returned.
	 * A token should be generated for authentication.
	 */
	public User login(String username, String password) throws FailedAuthenticationException {
		Optional<User> oUser = userRepo.findByUsername(username);
		if (oUser.isPresent()) {
			User user = oUser.get();
			String dbPass = user.getPassword();
			byte[] dbSalt = user.getSalt();

			String encodedPassword = password;
			if (dbSalt != null) {
				encodedPassword = WebUtils.encodePassword(encodedPassword, dbSalt);
			}

			// Password is correct
			if (dbPass.equals(encodedPassword)) {
				return user;
			}

			// Password is incorrect
			else {
				throw new FailedAuthenticationException();
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

		// Make a email checker

		// Encode the password with the salt
		byte[] salt = WebUtils.generateSalt();
		String encodedPassword = WebUtils.encodePassword(user.getPassword(), salt);
		user.setPassword(encodedPassword);
		user.setSalt(salt);
		return userRepo.save(user);
	}

	/**
	 * Updates the user. Throws a RecordNotFound exception if the user is
	 * not in the database. Returns the same object that was passed in if successful.
	 * @param user
	 * @return
	 * @throws RecordNotFoundException
	 */
	public User updateUser (User user) throws RecordNotFoundException {
		if (userRepo.existsUserByUsername(user.getUsername())) {
			userRepo.save (user);
			return user;
		}
		else {
			throw new RecordNotFoundException();
		}
	}

	/**
	 * Removes the user from the database. Throws a RecordNotFound
	 * exception if the user is not in the database. Returns the
	 * same object that was passed in if successful.
	 * @param user
	 * @return
	 * @throws RecordNotFoundException
	 */
	public User deleteUser (User user) throws RecordNotFoundException {
		if (userRepo.existsUserByUsername(user.getUsername())) {
			userRepo.delete(user);
			return user;
		}
		else {
			throw new RecordNotFoundException();
		}
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
