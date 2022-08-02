package com.revature.pokemondb.services;

import java.util.List;

import com.revature.pokemondb.exceptions.UsernameAlreadyExistsException;
import com.revature.pokemondb.models.User;

public interface UserService {
	public User getUserById (Long id);
	
	public User getUserByUsername (String username);

	public List<User> getAllUsers();

	public User login(String username, String password);
	
	public User registerUser(User user) throws UsernameAlreadyExistsException;
	
	public User banUser(User user);
	
	public User unBanUser(User user);
}
