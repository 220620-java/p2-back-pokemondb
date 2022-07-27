package com.revature.pokemondb.models;

/**
 * @author Colby Tang
 */
public class User {
	private int userId; 
	private String username;
	private String email; 
	private String password;
	private byte[] salt;

	public User () { }

    public User(int userId, String username, String email, String phone, String password) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		
		// Encrypt password
		// this.salt = WebUtils.generateSalt();
		// this.password = WebUtils.generateEncryptedPassword(password, this.salt);
	}
	
	public User(int userId, String username, String email, String password, byte[] salt) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.salt = salt;
		this.password = password;
	}
	
	/** 
	 * @return String
	 */
	@Override
	public String toString() {
		String retString = "UserId=%d, Username=%s, Email=%s, Password=%s, Salt=%s";
		return String.format(retString, getUserId(), getUsername(), getEmail(), getPassword(), getSalt());
	}

	/** 
	 * @return int
	 */
	public int getUserId() {
		return userId;
	}
	
	/** 
	 * @param customer_id
	 */
	public void setUserId(int customer_id) {
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

	// public void setEncryptedPassword () {
	// 	this.salt = WebUtils.generateSalt();
	// 	this.password = WebUtils.generateEncryptedPassword(this.password, this.salt);
	// }

	// public void setEncryptedPassword (String password) {
	// 	this.salt = WebUtils.generateSalt();
	// 	this.password = WebUtils.generateEncryptedPassword(password, this.salt);
	// }

	// public void setEncryptedPassword (String password, byte[] salt) {
	// 	this.salt = salt;
	// 	this.password = WebUtils.generateEncryptedPassword(password, this.salt);
	// }
}
