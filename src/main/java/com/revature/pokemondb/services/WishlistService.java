package com.revature.pokemondb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.models.Wishlist;
import com.revature.pokemondb.repositories.WishlistRepository;
import com.revature.pokemondb.repositories.PokemonRepository;

@Service
public class WishlistService {
    private WishlistRepository listRepo;
    private PokemonRepository pokemonRepo;
    
    public WishlistService(PokemonRepository pokemonRepo) {
        this.pokemonRepo = pokemonRepo;
    }

    // add pokemon to wish list
    public Boolean addWishlist(Wishlist wishlist) {

        try {
            listRepo.save(wishlist);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // delete pokemon from wish list
    public boolean deleteWishlist(int id )  {
        try {
            listRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //
    public List<Wishlist> getAllWishlists() {
        List<Wishlist> wishlists = listRepo.findAll();
        return wishlists;
    }

    public Wishlist findById(int id) {
        try {
            Optional<Wishlist> wishlist = listRepo.findById(id);

            if (wishlist.isPresent()) {
                return wishlist.get();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Wishlist createWishlist (int Id) {
        Wishlist wishlist = createWishlist(Id);
        listRepo.save(wishlist);
        return wishlist;
    }

}
