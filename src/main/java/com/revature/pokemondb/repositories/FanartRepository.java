package com.revature.pokemondb.repositories;

import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.Fanart;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FanartRepository extends JpaRepository<Fanart, Integer>{

}
