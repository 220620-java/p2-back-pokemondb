package com.revature.pokemondb.services;


import com.revature.pokemondb.models.Wishlist;
import com.revature.pokemondb.repositories.WishlistRepository;


public class WishlistService {
    private WishlistRepository listRepo;
    
    // add pokemon 
    public String getPokemonJSON(int pokemonId) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + pokemonId;
        return getJSON(url);
        
    }
}
