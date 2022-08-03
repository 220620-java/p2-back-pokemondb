package com.revature.pokemondb.services;

import java.util.List;

import com.revature.pokemondb.exceptions.RecordNotFound;
import com.revature.pokemondb.exceptions.UsernameAlreadyExistsException;
import com.revature.pokemondb.models.User;

public interface UserService {
	public User getUserById (Long id) throws RecordNotFound;
	
	public User getUserByUsername (String username) throws RecordNotFound;

	public List<User> getAllUsers();

	public User login(String username, String password);
	
	public User registerUser(User user) throws UsernameAlreadyExistsException;

	public User updateUser(User user) throws RecordNotFound;
	
	public User deleteUser(User user) throws RecordNotFound;

	public User banUser(User user);
	
	public User unBanUser(User user);
}
