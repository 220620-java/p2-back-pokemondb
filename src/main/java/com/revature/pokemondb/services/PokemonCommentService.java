package com.revature.pokemondb.services;

import java.util.List;

import com.revature.pokemondb.models.PokemonComments;

public interface PokemonCommentService {

    public List<PokemonComments> getAllFlaggedComments();
    
    public PokemonComments getById(Integer id);
    
    public List<PokemonComments> getAllUnflaggedComments();

    public PokemonComments storeNewComment(PokemonComments comment);

    public PokemonComments updateComment(PokemonComments comment);
    
    public PokemonComments deleteComment(PokemonComments comment);

}
