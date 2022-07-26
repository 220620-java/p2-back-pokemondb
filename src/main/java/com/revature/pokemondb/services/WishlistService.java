package com.revature.pokemondb.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.models.Wishlist;
import com.revature.pokemondb.models.dtos.PokemonDTO;
import com.revature.pokemondb.models.dtos.UserIdDTO;
import com.revature.pokemondb.repositories.WishlistRepository;
import com.revature.pokemondb.repositories.PokemonRepository;
import com.revature.pokemondb.repositories.UserRepository;

@Service
public class WishlistService {
    private WishlistRepository listRepo;
    private PokemonRepository pokemonRepo;
    private UserRepository userRepo;

    public WishlistService(PokemonRepository pokemonRepo, WishlistRepository listRepo, UserRepository userRepo) {
        this.pokemonRepo = pokemonRepo;
        this.listRepo = listRepo;
        this.userRepo = userRepo;
    }

    // add pokemon to wish list
    public Boolean addPokemonToWishlist(Wishlist pokemonid) {
        Wishlist wishlist = listRepo.save(pokemonid);
        if(wishlist == null) {
            return false;
        } else {
        return true;
        }
    }

    // delete pokemon from wish list
    public boolean deletePokemonFromWishlist(int pokemonid, int userid) {
        PokemonDTO pokeId = new PokemonDTO(pokemonid);
        UserIdDTO userId = new UserIdDTO(userid, "");
        
        try {
            // listRepo.deleteByPokemonAndUser(pokeId, userId);
            Wishlist wishlistdelete = listRepo.findByUserIdAndPokemonId(userid, pokemonid);
            listRepo.delete(wishlistdelete);
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

    public List<Wishlist> findByUserId(int id) {
        UserIdDTO userId = new UserIdDTO(id, "");
            List<Wishlist> wishlist = listRepo.findByUser(userId);

            return wishlist;
        
    }
}
