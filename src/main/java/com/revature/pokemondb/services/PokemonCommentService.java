package com.revature.pokemondb.services;

import com.revature.pokemondb.models.PokemonComments;

import java.util.Optional;

public interface PokemonCommentService {

    String getAllComments();

    PokemonComments storeNewComment(PokemonComments comment);

    void deleteComment(PokemonComments comment);

    Optional<PokemonComments> updateComment(PokemonComments comment);

}
