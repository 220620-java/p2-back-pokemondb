package com.revature.pokemondb.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.revature.pokemondb.models.PokemonComments;

import java.util.List;
import java.util.Optional;

public interface PokemonCommentService {

    List<JsonNode> getAllComments();

    Optional<PokemonComments> storeNewComment(JsonNode comment);

    Optional<PokemonComments> deleteComment(JsonNode comment);

    Optional<PokemonComments> updateComment(JsonNode comment);

}
