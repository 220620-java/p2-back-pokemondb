package com.revature.pokemondb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.models.Wishlist;
import com.revature.pokemondb.services.PokemonService;
import com.revature.pokemondb.services.UserService;
import com.revature.pokemondb.services.UserServiceImpl;
import com.revature.pokemondb.services.WishlistService;

@RestController
@RequestMapping(path = "/wishlist")
public class WishlistController {
    private UserService userService;
    private PokemonService pokemonService;
    private ObjectMapper objectMapper;
    private WishlistService wishlistService;

    public WishlistController(PokemonService pokemonService, ObjectMapper objectMapper, WishlistService wishlistService,
            UserService userService) {
        this.pokemonService = pokemonService;
        this.objectMapper = objectMapper;
        this.wishlistService = wishlistService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Wishlist>> getAllWishlists() {
        List<Wishlist> wishlist = wishlistService.getAllWishlists();
        if (wishlist != null) {
            return ResponseEntity.ok(wishlist);
        } 
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<String> getWishlistById(@PathVariable int id) {
        List<Wishlist> wishlist = wishlistService.findByUserId(id);
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

    @PostMapping()
    public ResponseEntity<String> addPokemon(@RequestBody Wishlist wish) {
        if (wish != null) {
            wishlistService.addPokemonToWishlist(wish);
            return ResponseEntity.ok("add a wishlist");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/")
    public ResponseEntity<String> deletePokemon(@RequestBody Map<String, Integer> parameters) {
        Integer pokemonid = parameters.get("pokemonid");
        Integer userid = parameters.get("userid");
        wishlistService.deletePokemonFromWishlist(pokemonid, userid);
        return new ResponseEntity<String>("Pokemon is free", HttpStatus.OK);
    }
}
