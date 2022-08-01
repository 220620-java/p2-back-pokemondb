package com.revature.pokemondb.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.services.PokemonService;
import com.revature.pokemondb.services.WishlistService;

@RestController
@RequestMapping(path="/wishlist")
public class WishlistController {
    private PokemonService pokemonService;
    private ObjectMapper objectMapper;
    private WishlistService wishlistService;

    public WishlistController(PokemonService pokemonService, ObjectMapper objectMapper, WishlistService wishlistService) {
        this.pokemonService = pokemonService;
        this.objectMapper = objectMapper;
        this.wishlistService = wishlistService;
    }

    @GetMapping("/")
    public ResponseEntity<String> getAllPokemon() {
        List<Pokemon> pokemon = pokemonService.getPokemonJSON();
        String pokemonJSON;
        try {
            pokemonJSON = objectMapper.writeValueAsString(pokemon);
            if (pokemon != null) {
                return ResponseEntity.ok(pokemonJSON);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<String> getPokemonById(@PathVariable int id) {
        Pokemon pokemon = pokemonService.getPokemonJSON(id);
        String pokemonJSON;
        try {
            pokemonJSON = objectMapper.writeValueAsString(pokemon);
            if (pokemon != null) {
                return ResponseEntity.ok(pokemonJSON);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }
}
