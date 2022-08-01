package com.revature.pokemondb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
	public Optional<Pokemon> findByName(String name);
}
