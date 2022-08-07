package com.revature.pokemondb.services;

import java.util.List;

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
    public List<PokemonDTO> getAllPokemonById (List<Integer> ids);
}
