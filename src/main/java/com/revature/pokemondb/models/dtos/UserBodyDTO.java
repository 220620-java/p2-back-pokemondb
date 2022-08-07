package com.revature.pokemondb.models.dtos;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

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

	public UserBodyDTO (long id) {
		this.userId = id;
		this.username = "";
		this.email = "";
		this.password = "";
		this.token = "";
		this.role = "user";
	}

	public UserBodyDTO(String username, String email) {
		this.username = username;
		this.email = email;
	}

	public UserBodyDTO(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	@Autowired
    public UserBodyDTO(Long userId, String username, String email, String password) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public UserBodyDTO(Long userId, String username, String email, String password, byte[] salt) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.salt = salt;
		this.password = password;
	}

	public UserBodyDTO(Long userId, String username, String email, String password, byte[] salt, String token) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.salt = salt;
		this.password = password;
		this.token = token;
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

	public UserBodyDTO (Map<String, String> map) {
		if ((map.get("userId") != null)) {
			this.userId = Long.valueOf(map.get("userId"));
		}

		this.username = map.get("username");

		this.email = map.get("email");

		if (map.get("salt") != null) {
			this.salt = map.get("salt").getBytes();
		}

		this.password = map.get("password");

		if (map.get("role") != null) {
			this.role = map.get("role");
		}
		else {
			this.role = "user";
		}
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
		UserBodyDTO other = (UserBodyDTO) obj;
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
}
