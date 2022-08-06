package com.revature.pokemondb.models.dtos;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Barry Norton
 *
 */
@Entity
@Table(name = "fanart_comments", schema = "pokemon_db")
public class ArtCommDTO {
	@Id
	private int id;
	
	/*Constructors*/
	
	public ArtCommDTO() {
		this.id = 0;
	}
	
	public ArtCommDTO(int id) {
		super();
		this.id = id;
	}

	/*Overrides*/
	
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
		ArtCommDTO other = (ArtCommDTO) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "FanartDTO [id=" + id + "]";
	}
	
	/*Getters and Setters*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}