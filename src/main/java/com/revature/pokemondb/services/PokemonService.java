package com.revature.pokemondb.services;

import java.util.List;

import com.revature.pokemondb.models.Pokemon;

public interface PokemonService {
    public String getRequestJSON(String url);
    public String getPokemonJSON(int pokemonId);
    public String getPokemonJSON(String pokemonName);
    public Pokemon getReferencePokemon(int id);
    public Pokemon getReferencePokemon(String name);
    public Pokemon createPokemon (int pokemonId);
    public Pokemon createPokemon (String pokemonName);
    public Pokemon createPokemonObject (String pokemonJSON);
    public List<Pokemon> getAllPokemonById (List<Integer> ids);
}
