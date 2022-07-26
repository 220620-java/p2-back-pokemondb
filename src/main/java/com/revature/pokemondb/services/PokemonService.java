package com.revature.pokemondb.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonService {
    
    private final RestTemplate restTemplate;

    public PokemonService (RestTemplateBuilder restBuilder) {
        this.restTemplate = restBuilder.build();
    }

    // Get a default pokemon: pikachu
    public String getPokemon() {
        String url = "https://pokeapi.co/api/v2/pokemon/pikachu";
        return this.restTemplate.getForObject(url, String.class);
    }

    // Grab pokemon by name
    public String getPokemon(String pokemonName) {
        // Convert name to lowercase and replace whitespaces with hyphens
        pokemonName = pokemonName.toLowerCase();
        pokemonName = pokemonName.replace(' ', '-');
        String url = "https://pokeapi.co/api/v2/pokemon/" + pokemonName;
        return this.restTemplate.getForObject(url, String.class);
    }
}
