package com.revature.pokemondb.repositories;

import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.ArtComment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Barry Norton
 *
 */
@Repository
public interface ArtCommRepository extends JpaRepository<ArtComment, Integer>{
	
}
