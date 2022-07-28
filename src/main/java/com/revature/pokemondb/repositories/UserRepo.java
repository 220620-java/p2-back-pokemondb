package com.revature.pokemondb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
