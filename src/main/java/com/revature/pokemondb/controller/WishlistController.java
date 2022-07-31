package com.revature.pokemondb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
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
}
