package com.revature.pokemondb.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.models.Wishlist;
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
    public ResponseEntity<String> getAllWishlists() {
        List<Wishlist> wishlist = wishlistService.getAllWishlists();
        String wishlistJSON;
        try {
            wishlistJSON = objectMapper.writeValueAsString(wishlist);
            if (wishlist != null) {
                return ResponseEntity.ok(wishlistJSON);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<String> getWishlistById(@PathVariable int id) {
        Wishlist wishlist = wishlistService.findById(id);
        String wishlistJSON;
        try {
            wishlistJSON = objectMapper.writeValueAsString(wishlist);
            if (wishlist != null) {
                return ResponseEntity.ok(wishlistJSON);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/create")
    public ResponseEntity<String> createWishlist(@RequestBody Wishlist wishlist) {
        Boolean success = true;
        success = wishlistService.addWishlist(wishlist);
        if (success) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }
}
