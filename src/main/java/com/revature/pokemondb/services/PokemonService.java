package com.revature.pokemondb.services;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.models.dtos.PokemonDTO;

public interface PokemonService {
    public String getPokemonJSON(int pokemonId);
    public String getPokemonJSON(String pokemonName);
    public PokemonDTO getReferencePokemon(int id);
    public PokemonDTO getReferencePokemon(String name);
    public Pokemon createPokemon (int pokemonId);
    public Pokemon createPokemon (String pokemonName);
    public Pokemon createPokemonObject (String pokemonJSON);
    public PokemonDTO createReferencePokemon (String pokemonName);
    public PokemonDTO createReferencePokemon (Integer pokemonId);
    public PokemonDTO createReferencePokemonObject (String pokemonJSON, String pokemonSpeciesJSON) throws JsonMappingException, JsonProcessingException;
    public List<PokemonDTO> getAllPokemonById (List<Integer> ids);
}
