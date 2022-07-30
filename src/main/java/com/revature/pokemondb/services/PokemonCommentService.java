package com.revature.pokemondb.services;

import com.revature.pokemondb.models.PokemonComments;

public interface PokemonCommentService {

    PokemonComments getAllComments();

    PokemonComments storeNewComment( PokemonComments comment);

    PokemonComments deleteComment(PokemonComments comment);

    PokemonComments flagComment(PokemonComments comment);

    PokemonComments likeComment(PokemonComments comment);
}
