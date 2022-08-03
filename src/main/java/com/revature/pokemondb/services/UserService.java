package com.revature.pokemondb.services;

import java.util.List;

import com.revature.pokemondb.exceptions.FailedAuthenticationException;
import com.revature.pokemondb.exceptions.RecordNotFoundException;
import com.revature.pokemondb.exceptions.UsernameAlreadyExistsException;
import com.revature.pokemondb.models.User;

public interface UserService {
	public User getUserById (Long id) throws RecordNotFoundException;
	
	public User getUserByUsername (String username) throws RecordNotFoundException;

	public List<User> getAllUsers();

	public User login(String username, String password) throws FailedAuthenticationException;
	
	public User registerUser(User user) throws UsernameAlreadyExistsException;

	public User updateUser(User user) throws RecordNotFoundException;
	
	public User deleteUser(User user) throws RecordNotFoundException;

	public User banUser(User user);
	
	public User unBanUser(User user);
}
