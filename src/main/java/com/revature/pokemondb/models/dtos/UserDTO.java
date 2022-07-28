package com.revature.pokemondb.models.dtos;

import com.revature.pokemondb.models.User;

/** 
 * A Data Transfer version of User
 * @author Colby Tang
 */
public class UserDTO {
	private int userId; 
	private Long userId;
	private String username;
	private String email; 
	private String token = "";

	public UserDTO () {}

    public UserDTO(int userId, String username, String email) {
    public UserDTO(Long userId, String username, String email) {
		this.userId = userId;
		this.username = username;
		this.email = email;
	}
	 
	public UserDTO (User user) {
		this.userId = user.getUserId();
		this.username = user.getUsername();
		this.email = user.getEmail();
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
	public int getUserId() {
	public Long getUserId() {
		return userId;
	}
	
	/** 
	 * @param customer_id
	 */
	public void setUserId(int customer_id) {
	public void setUserId(Long customer_id) {
		this.userId = customer_id;
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
