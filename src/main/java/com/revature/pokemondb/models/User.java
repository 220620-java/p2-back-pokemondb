package com.revature.pokemondb.models;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Colby Tang
 */
@Entity
@Table(name = "users", schema = "pokemon_db")
public class User {
	@Id
	@Column(name="id", updatable=false, insertable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId; 
	private String username;
	private String email; 
	@Column(name = "passwd")
	private String password;
	private byte[] salt;

	public User () {
		this.userId = 0l;
		this.username = "";
		this.email = "";
		this.password = "";
		
	}

	@Autowired
    public User(Long userId, String username, String email, String phone, String password) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		
		// Encrypt password
		// this.salt = WebUtils.generateSalt();
		// this.password = WebUtils.generateEncryptedPassword(password, this.salt);
	}
	
	public User(Long userId, String username, String email, String password, byte[] salt) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.salt = salt;
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(salt);
		result = prime * result + Objects.hash(email, password, userId, username);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Arrays.equals(salt, other.salt) && Objects.equals(userId, other.userId)
				&& Objects.equals(username, other.username);
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
	public Long getUserId() {
		return userId;
	}
	
	/** 
	 * @param customer_id
	 */
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
