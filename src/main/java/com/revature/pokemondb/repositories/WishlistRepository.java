package com.revature.pokemondb.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.models.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    public List<Long> findByIdContaining(Long id);
    @Modifying
    @Query(
        value = "INSERT INTO pokemon_wishlists (pokemon_id, user_id)" +
                "VALUES (:pokemon_id, :user_id)", nativeQuery = true 
    )
    public void saveWishlist(@Param("pokemon_id") Pokemon pokemon_id, @Param("user_id") User user_id);

    
}
