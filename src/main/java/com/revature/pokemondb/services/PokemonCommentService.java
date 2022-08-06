package com.revature.pokemondb.services;

import com.revature.pokemondb.models.PokemonComments;

public interface PokemonCommentService {

    String getAllComments();

    PokemonComments storeNewComment(PokemonComments comment);

    void deleteComment(PokemonComments comment);

    PokemonComments updateComment(PokemonComments comment);

}
