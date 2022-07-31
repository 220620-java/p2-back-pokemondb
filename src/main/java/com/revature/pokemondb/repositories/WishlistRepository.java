package com.revature.pokemondb.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    void save(int pokemonId);
    
}
