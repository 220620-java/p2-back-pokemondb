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
@Table(name = "pokemon_fanart", schema = "pokemon_db")
public class FanartDTO {
	@Id
	private int id;
	
	/*Constructors*/
	
	public FanartDTO() {
		this.id = 0;
	}
	
	public FanartDTO(int id) {
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
		FanartDTO other = (FanartDTO) obj;
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