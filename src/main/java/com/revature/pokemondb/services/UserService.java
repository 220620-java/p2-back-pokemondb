package com.revature.pokemondb.services;

import com.revature.pokemondb.models.User;

public interface UserService {
	
	public User login(String username, String password);
	
	public User registerUser(User user);
	
	public User banUser(User user);
	
	public User unBanUser(User user);
}
