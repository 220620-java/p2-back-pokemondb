package com.revature.pokemondb.services;

import com.revature.pokemondb.exceptions.UsernameAlreadyExistsException;
import com.revature.pokemondb.models.User;

public interface UserService {
	public User getUserById (Integer userId);
	
	public User getUserByUsername (String username);

	public User login(String username, String password);
	
	public User registerUser(User user) throws UsernameAlreadyExistsException;
	
	public User banUser(User user);
	
	public User unBanUser(User user);
}
