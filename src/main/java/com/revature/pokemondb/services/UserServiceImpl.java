package com.revature.pokemondb.services;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.pokemondb.exceptions.EmailAlreadyExistsException;
import com.revature.pokemondb.exceptions.FailedAuthenticationException;
import com.revature.pokemondb.exceptions.InvalidInputException;
import com.revature.pokemondb.exceptions.RecordNotFoundException;
import com.revature.pokemondb.exceptions.UsernameAlreadyExistsException;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.repositories.UserRepository;
import com.revature.pokemondb.utils.SecurityUtils;

@Service("userService")
public class UserServiceImpl implements UserService {
	private UserRepository userRepo;
	private SecurityUtils securityUtils;

	public UserServiceImpl (UserRepository repo, SecurityUtils utils) {
		this.userRepo = repo;
		this.securityUtils = utils;
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
	public User loginUser(String username, String password) throws RecordNotFoundException, FailedAuthenticationException, NoSuchAlgorithmException {
		Optional<User> oUser = userRepo.findByUsername(username);
		if (oUser.isPresent()) {
			User user = oUser.get();
			String dbPass = user.getPassword();
			byte[] dbSalt = user.getSalt();

			String encodedPassword = password;
			if (dbSalt != null) {
				try {
					encodedPassword = securityUtils.encodePassword(encodedPassword, dbSalt);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
					throw new NoSuchAlgorithmException();
				}
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
		throw new RecordNotFoundException();
	}
	
	/**
	 * Inserts the user into the database. Throws an exception if username/email already exists.
	 * @throws InvalidInputException
	 */
	public User registerUser(User user) throws UsernameAlreadyExistsException, EmailAlreadyExistsException, NoSuchAlgorithmException, InvalidInputException {
		// Does the username already exist in the database
		if (userRepo.existsUserByUsername(user.getUsername())) {
			throw new UsernameAlreadyExistsException();
		}

		// Does user's email already exist in the database
		if (userRepo.existsUserByEmail(user.getEmail())) {
			throw new EmailAlreadyExistsException();
		}

		// Is the password null or empty?
		if (user.getPassword() == null || user.getPassword().equals("")) {
			throw new InvalidInputException();
		}

		// Encode the password with the salt
		byte[] salt;
		try {
			salt = securityUtils.generateSalt();
			String encodedPassword = securityUtils.encodePassword(user.getPassword(), salt);
			user.setPassword(encodedPassword);
			user.setSalt(salt);

			// Save the user to the database
			return userRepo.save(user);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new NoSuchAlgorithmException();
		}
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
