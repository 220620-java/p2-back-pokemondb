package com.revature.pokemondb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.PokemonComments;

import javax.persistence.Column;
import java.util.List;
import java.util.Optional;

@Repository
public interface PokemonCommentRepo extends JpaRepository<PokemonComments, Long> {

    List<PokemonComments> findAllByIsflaggedFalse();

}
