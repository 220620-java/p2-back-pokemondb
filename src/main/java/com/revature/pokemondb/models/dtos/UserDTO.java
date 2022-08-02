package com.revature.pokemondb.models.dtos;

import com.revature.pokemondb.models.User;

/** 
 * A Data Transfer version of User
 * @author Colby Tang
 */
public class UserDTO {
	private Long userId;
	private String username;
	private String email;
	private String role = "user";
	private String token = "";

	public UserDTO () {}

    public UserDTO(Long userId, String username, String email) {
		this.userId = userId;
		this.username = username;
		this.email = email;
	}

	public UserDTO(Long userId, String username) {
		this.userId = userId;
		this.username = username;
	}
	
	public UserDTO (User user) {
		this.userId = user.getUserId();
		this.username = user.getUsername();
		this.email = user.getEmail();
	}
	 
	public UserDTO (User user, String token) {
		this.userId = user.getUserId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.token = token;
	}

	/** 
	 * @return String
	 */
	@Override
	public String toString() {
		String retString = "UserId=%d, Username=%s, Email=%s";
		retString = String.format(retString, getUserId(), getUsername(), getEmail());
		return retString;
	}
	
	/** 
	 * @return int
	 */
	public Long getUserId() {
		return userId;
	}
	
	/** 
	 * @param customer_id
	 */
	public void setUserId(Long id) {
		this.userId = id;
	}
	
	/** 
	 * @return String
	 */
	public String getUsername() {
		return username;
	}
	
	/** 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/** 
	 * @return String
	 */
	public String getEmail() {
		return email;
	}
	
	/** 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets role name with all lower case
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role.toLowerCase();
	}

	/** 
	 * @return String
	 */
	public String getToken() {
		return token;
	}

	/** 
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}
}
