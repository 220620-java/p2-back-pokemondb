package com.revature.pokemondb.services;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.pokemondb.exceptions.BannedException;
import com.revature.pokemondb.exceptions.EmailAlreadyExistsException;
import com.revature.pokemondb.exceptions.FailedAuthenticationException;
import com.revature.pokemondb.exceptions.InvalidInputException;
import com.revature.pokemondb.exceptions.RecordNotFoundException;
import com.revature.pokemondb.exceptions.UsernameAlreadyExistsException;
import com.revature.pokemondb.models.BannedUser;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.repositories.BanRepository;
import com.revature.pokemondb.repositories.UserRepository;
import com.revature.pokemondb.utils.SecurityUtils;

@Service("userService")
public class UserServiceImpl implements UserService {
	private UserRepository userRepo;
	private BanRepository banRepo;
	private SecurityUtils securityUtils;

	public UserServiceImpl (UserRepository repo, BanRepository banRepo, SecurityUtils utils) {
		this.userRepo = repo;
		this.banRepo = banRepo;
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
	 * @throws BannedException
	 */
	public User loginUser(String username, String password) throws RecordNotFoundException, FailedAuthenticationException, NoSuchAlgorithmException, BannedException {
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

				Optional<BannedUser> oBannedUser = banRepo.findById(user.getUserId());
				// Is user in the ban table?
				if (oBannedUser.isPresent()) {
					
					BannedUser bannedUser = oBannedUser.get();
					Timestamp banDuration = bannedUser.getBanDuration();

					// Has user's ban duration expired?
					if (banDuration.getTime() < Timestamp.from(Instant.now()).getTime()) {
						unBanUser (user.getUserId());
						return user;
					}

					// Nope lol stay banned
					else {
						throw new BannedException();
					}
				}
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
	 * @throws NoSuchAlgorithmException
	 */
	public User updateUser (User user) throws RecordNotFoundException, NoSuchAlgorithmException {
		if (userRepo.existsUserByUsername(user.getUsername())) {
			Optional<User> oUser = userRepo.findByUsername(user.getUsername());
			if (oUser.isEmpty()) return null;
			User dbUser = oUser.get();
			
			// User ID
			if (user.getUserId() != 0) dbUser.setUserId(user.getUserId());

			// Username
			if (!user.getUsername().equals("") && user.getUsername() != null) dbUser.setUsername(user.getUsername());

			// Email
			if (!user.getEmail().equals("") && user.getEmail() != null) dbUser.setEmail(user.getEmail());

			// Password
			if (!user.getPassword().equals("") && user.getPassword() != null) {
				byte[] salt = securityUtils.generateSalt();
				dbUser.setSalt(salt);
				String encodedPassword = securityUtils.encodePassword(user.getPassword(), dbUser.getSalt());
				dbUser.setPassword(encodedPassword);
			}

			// Role
			if (!user.getRole().equals("") && user.getRole() != null) dbUser.setRole(user.getRole());

			// Update user in DB
			userRepo.save (dbUser);
			return dbUser;
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
			Optional<User> oUser = userRepo.findByUsername(user.getUsername());
			if (oUser.isEmpty()) return null;
			User dbUser = oUser.get();

			userRepo.delete(dbUser);
			return dbUser;
		}
		else {
			throw new RecordNotFoundException();
		}
	}
	
	/**
	 * Inserts the user into the ban table to indicate
	 * a user has been banned.
	 * @throws UsernameAlreadyExistsException
	 * @throws RecordNotFoundException
	 */
	public User banUser(BannedUser bannedUser) throws UsernameAlreadyExistsException, RecordNotFoundException {
		Optional<User> oUser = userRepo.findById(bannedUser.getUserId());
		if (oUser.isPresent()) {
			User dbUser = oUser.get();

			// If user is not already banned
			if (!banRepo.existsById(dbUser.getUserId())) {
				banRepo.save(bannedUser);
				return dbUser;
			}
			else {
				throw new UsernameAlreadyExistsException();
			}

		}

		// Cannot find username in users table
		throw new RecordNotFoundException();
	}
	
	/**
	 * Removes a user from the ban table to indicate
	 * a user has been unbanned.
	 * @throws RecordNotFoundException
	 */
	public User unBanUser(Long id) throws RecordNotFoundException {
		Optional<BannedUser> oBannedUser = banRepo.findById(id);

		// If user is in the ban table
		if (oBannedUser.isPresent()) {
			Optional<User> oUser = userRepo.findById(id);

			// If user is in the users table
			if (oUser.isPresent()) {
				banRepo.delete(oBannedUser.get());
				return oUser.get();
			}
			banRepo.delete(oBannedUser.get());
			return null;
		}
		else {
			throw new RecordNotFoundException();
		}
	}
}
