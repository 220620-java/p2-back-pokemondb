package com.revature.pokemondb.repositories;

import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.ReportArt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author
 *
 */
@Repository
public interface ReportArtRepository extends JpaRepository<ReportArt, Integer>{
	
}
