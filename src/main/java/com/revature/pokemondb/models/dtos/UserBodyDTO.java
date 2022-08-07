package com.revature.pokemondb.models.dtos;

import com.revature.pokemondb.models.User;

/**
 * This DTO is for accepting User objects through a request
 * @author Colby Tang
 */
public class UserBodyDTO {
	private Long userId = 0l; 
	private String username;
	private String email;
	private String role;
	private String password;
	private String token;
	private byte[] salt;

	public UserBodyDTO () {
		this.userId = 0l;
		this.username = "";
		this.email = "";
		this.password = "";
		this.token = "";
		this.role = "user";
	}

	public UserBodyDTO (User user) {
		this.userId = user.getUserId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.salt = user.getSalt();
		this.password = user.getPassword();
		this.role = user.getRole();
	}

	public UserBodyDTO (UserDTO user) {
		this.userId = user.getUserId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.role = user.getRole();
		this.token = user.getToken();
	}

	/** 
	 * @return int
	 */
	public Long getUserId() {
		return userId;
	}
	
	/** 
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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
	 * @return String
	 */
	public String getPassword() {
		return password;
	}
	
	/** 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 
	 * @return byte[]
	 */
	public byte[] getSalt() {
        return salt;
    }

	/** 
	 * @param salt
	 */
	public void setSalt(byte[] salt) {
        this.salt = salt;
    }

	public String getRole() {
		return role;
	}

	public void setRole (String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
