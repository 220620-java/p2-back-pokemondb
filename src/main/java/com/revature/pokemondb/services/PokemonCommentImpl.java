package com.revature.pokemondb.services;

import com.revature.pokemondb.models.PokemonComments;
import com.revature.pokemondb.models.User;
import org.springframework.stereotype.Service;

@Service
public class PokemonCommentImpl implements PokemonCommentService{

    @Override
    public PokemonComments getAllComments() {
        return null;
    }

    @Override
    public PokemonComments storeNewComment(User user, PokemonComments comment) {
        return null;
    }

    @Override
    public PokemonComments deleteComment(PokemonComments comment) {
        return null;
    }

    @Override
    public PokemonComments flagComment(PokemonComments comment) {
        return null;
    }

    @Override
    public PokemonComments likeComment(PokemonComments comment) {
        return null;
    }
}
