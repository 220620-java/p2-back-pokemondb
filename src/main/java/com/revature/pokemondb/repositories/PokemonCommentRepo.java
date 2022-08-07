package com.revature.pokemondb.repositories;

import com.revature.pokemondb.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.PokemonComments;

import java.util.List;

@Repository
public interface PokemonCommentRepo extends JpaRepository<PokemonComments, Long> {

    List<PokemonComments> findAllByIsflaggedFalse();
    List<PokemonComments> findAllByIsflaggedTrue();
    List<PokemonComments> findAllByPokemon(Pokemon pokemon);

}
