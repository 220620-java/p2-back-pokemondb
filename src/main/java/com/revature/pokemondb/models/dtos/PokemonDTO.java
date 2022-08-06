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
@Table(name = "pokemon", schema = "pokemon_db")
public class PokemonDTO {
	@Id
	@Column
	private int id;

	public PokemonDTO(int id) {
		this.id = id;
	}

	public PokemonDTO() {
		this.id = 0;
	}

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

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
