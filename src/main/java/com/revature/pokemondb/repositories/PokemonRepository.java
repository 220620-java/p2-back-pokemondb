package com.revature.pokemondb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.dtos.PokemonDTO;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonDTO, Integer> {
	public Optional<PokemonDTO> findByName(String name);
}
