package com.revature.pokemondb.models.dtos;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.revature.pokemondb.models.User;

/** 
 * @author Barry Norton
 */
@Entity
@Table(name="users", schema="pokemon_db")
public class UserIdDTO {
	@Id
	private int id;

	/*Constructor*/
	
    public UserIdDTO(int id) {
		this.id = id;
	}
	 
	public UserIdDTO() {
		this.id = 0;
	}
	
	/*Overrides*/
	
	@Override
	public String toString() {
		return "UserIdDTO [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserIdDTO other = (UserIdDTO) obj;
		return id == other.id;
	}

	/*Getters and Setters*/
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
