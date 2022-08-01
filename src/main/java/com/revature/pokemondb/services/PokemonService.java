package com.revature.pokemondb.services;

import com.revature.pokemondb.models.Pokemon;

public interface PokemonService {
    public String getJSON(String url);
    public String getPokemon();
    public String getPokemonJSON(int pokemonId);
    public String getPokemonJSON(String pokemonName);
    public String getPokemonSpeciesJSON(String pokemonName);
    public String getEvolutionChainJSON(int evolutionID);
    public Pokemon createPokemon (int pokemonId);
    public Pokemon createPokemon (String pokemonName);
    public Pokemon createPokemonObject (String pokemonJSON);
}
