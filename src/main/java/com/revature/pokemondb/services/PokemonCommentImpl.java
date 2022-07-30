package com.revature.pokemondb.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.PokemonComments;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.repositories.PokemonCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonCommentImpl implements PokemonCommentService{
    private PokemonCommentRepository pokeRepo;
    private ObjectMapper JsonMapper;

    @Autowired
    public PokemonCommentImpl(PokemonCommentRepository pokeRepo, ObjectMapper JsonMapper) {

    }

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
