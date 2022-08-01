package com.revature.pokemondb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public List<Pokemon> getAll() {
        return pokemonService.getJSON();
    }
}
