package com.revature.pokemondb.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.models.Wishlist;
import com.revature.pokemondb.repositories.WishlistRepository;
import com.revature.pokemondb.repositorys.PokemonRepo;

@Service
public class WishlistService {
    private WishlistRepository listRepo;
    private ObjectMapper objectMapper = new ObjectMapper();

    private final RestTemplate restTemplate;

    public WishlistService(RestTemplateBuilder restBuilder) {
        this.restTemplate = restBuilder.build();
    }

    public String getJSON(String url) {
        return this.restTemplate.getForObject(url, responseType: String.class);
    }

    // add pokemon to wish list
    public String addPokemonJSONString(int pokemonId) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + pokemonId;
        listRepo.save(pokemonId);
        return getJSON(url);

    }
    
    // delete pokemon from wish list
    public Wishlist deletePokemon(Wishlist pokemonid) {
        listRepo.delete(pokemonid);
        return null;
    }
}
