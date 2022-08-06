package com.revature.pokemondb.repositories;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);
    public boolean existsUserByUsername(String username);
    public boolean existsUserByEmail(String email);
}
