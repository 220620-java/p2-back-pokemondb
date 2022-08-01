package com.revature.pokemondb.services;

import java.util.List;

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
    private PokemonRepository pokemonRepo;
    
    public WishlistService(PokemonRepository pokemonRepo) {
        this.pokemonRepo = pokemonRepo;
    }

    // add pokemon to wish list
    public Boolean addPokemon(int pokemonid) {
        try {
            pokemonRepo.add(pokemonid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // delete pokemon from wish list
    public boolean deletePokemon(int pokemonid) {
        try {
            listRepo.deleteById(pokemonid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Pokemon> getAllPokemon() {
        List<Pokemon> pokemon = pokemonRepo.findAll(false);
        return pokemon;
    }
}
