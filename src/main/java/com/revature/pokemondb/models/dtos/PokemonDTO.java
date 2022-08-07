package com.revature.pokemondb.models.dtos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.revature.pokemondb.models.Pokemon;

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

	@Column(name="name")
    private String name;

	
	@Column(name="sprite")
	private String imageUrl;


	@Column(name="gen")
    private int generation;

	public PokemonDTO() {
		this.id = 0;
	}

	public PokemonDTO(int id) {
		this.id = id;
	}

	public PokemonDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public PokemonDTO(int id, String name, String imageUrl) {
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
	}

	public PokemonDTO(int id, String name, String imageUrl, int generation) {
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.generation = generation;
	}

	public PokemonDTO (Pokemon pokemon) {
		this.id = pokemon.getId();
		this.name = pokemon.getName();
		this.imageUrl = pokemon.getImageUrl();
		this.generation = pokemon.getGeneration();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
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
}
