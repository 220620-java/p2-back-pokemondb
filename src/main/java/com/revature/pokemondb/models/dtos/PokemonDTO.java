package com.revature.pokemondb.models.dtos;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Barry Norton
 */
@Entity
@Table(name = "pokemon", schema = "pokemon_db")
public class PokemonDTO {
	@Id
	private int id;

	/* Constructor */

	public PokemonDTO(int id) {
		this.id = id;
	}

	public PokemonDTO() {
		this.id = 0;
	}

	/* Overrides */

	@Override
	public String toString() {
		return "PokemonDTO [id=" + id + "]";
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

	/* Getters and Setters */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
