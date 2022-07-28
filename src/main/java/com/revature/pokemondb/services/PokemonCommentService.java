package com.revature.pokemondb.services;

import com.revature.pokemondb.models.PokemonComments;
import com.revature.pokemondb.models.User;

public interface PokemonCommentService {

    PokemonComments getAllComments();

    PokemonComments storeNewComment(User user, PokemonComments comment);

    PokemonComments deleteComment(PokemonComments comment);

    PokemonComments flagComment(PokemonComments comment);

    PokemonComments likeComment(PokemonComments comment);
}
