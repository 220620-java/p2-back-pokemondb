package com.revature.pokemondb.models.dtos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/** 
 * @author Barry Norton
 */
@Entity
@Table(name="pokemon", schema="pokemon_db")
public class PokemonDTO {
	@Id
	@Column
	private Long id;

	/*Constructor*/

    public PokemonDTO(Long id) {
		this.id = id;
	}

	public PokemonDTO() {
		this.id = 0l;
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
		PokemonDTO other = (PokemonDTO) obj;
		return id == other.id;
	}

	/*Getters and Setters*/
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
