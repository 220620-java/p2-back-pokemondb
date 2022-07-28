package com.revature.pokemondb.services;

import com.revature.pokemondb.models.User;

public interface UserServiceImpl {
	
	User login(String username, String password);
	
	User registerUser(User user);
	
	User banUser(User user);
	
	User unBanUser(User user);
}
