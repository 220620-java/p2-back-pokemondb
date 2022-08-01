package com.revature.pokemondb.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.revature.pokemondb.models.PokemonComments;

import java.util.List;
import java.util.Optional;

public interface PokemonCommentService {

    String getAllComments();

    Optional<PokemonComments> storeNewComment(PokemonComments comment);

    void deleteComment(PokemonComments comment);

    Optional<PokemonComments> updateComment(PokemonComments comment);

}
