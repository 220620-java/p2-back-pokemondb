package com.revature.pokemondb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.PokemonComments;

@Repository
public interface PokemonCommentRepo extends JpaRepository<PokemonComments, Long> {

}
