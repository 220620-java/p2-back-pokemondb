package com.revature.pokemondb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.pokemondb.models.PokemonComments;


public interface CommentRepository extends JpaRepository<PokemonComments, Integer>{

}
