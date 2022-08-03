package com.revature.pokemondb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.pokemondb.exceptions.RecordNotFound;
import com.revature.pokemondb.exceptions.UsernameAlreadyExistsException;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserServiceImpl (UserRepository repo) {
		this.userRepo = repo;
	}

	/**
	 * Retrieves a user by its ID.
	 * @param id
	 * @return an Optional User is returned
	 */
	public User getUserById (Long id) throws RecordNotFound {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) { return user.get();}
		else { throw new RecordNotFound();}
	}

	/**
	 * Retrieves a user by its username.
	 * @param username
	 * @return a User is returned
	 */
	public User getUserByUsername (String username) throws RecordNotFound {
		Optional<User> user = userRepo.findByUsername(username);
		if (user.isPresent()) { return user.get(); }
		else { throw new RecordNotFound();}
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
	public User login(String username, String password) {
		Optional<User> oUser = userRepo.findByUsername(username);
		if (oUser.isPresent()) {
			User user = oUser.get();
			String dbPass = user.getPassword();
			String encodedPassword = passwordEncoder.encode(password);
			// Password is correct
			if (passwordEncoder.matches(dbPass, encodedPassword)) {
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

		// Make a email checker

		// About BCryptPasswordEncoder https://stackabuse.com/password-encoding-with-spring-security/
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepo.save(user);
	}

	/**
	 * Updates the user. Throws a RecordNotFound exception if the user is
	 * not in the database. Returns the same object that was passed in if successful.
	 * @param user
	 * @return
	 * @throws RecordNotFound
	 */
	public User updateUser (User user) throws RecordNotFound {
		if (userRepo.existsUserByUsername(user.getUsername())) {
			userRepo.save (user);
			return user;
		}
		else {
			throw new RecordNotFound();
		}
	}

	/**
	 * Removes the user from the database. Throws a RecordNotFound
	 * exception if the user is not in the database. Returns the
	 * same object that was passed in if successful.
	 * @param user
	 * @return
	 * @throws RecordNotFound
	 */
	public User deleteUser (User user) throws RecordNotFound {
		if (userRepo.existsUserByUsername(user.getUsername())) {
			userRepo.delete(user);
			return user;
		}
		else {
			throw new RecordNotFound();
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
