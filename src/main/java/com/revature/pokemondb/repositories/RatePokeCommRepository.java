package com.revature.pokemondb.repositories;

import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.RatePokeComm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author
 *
 */
@Repository
public interface RatePokeCommRepository extends JpaRepository<RatePokeComm, Integer>{
	
}
