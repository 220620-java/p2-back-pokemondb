package com.revature.pokemondb.repositories;

import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.RateArt;
import com.revature.pokemondb.models.dtos.FanartDTO;
import com.revature.pokemondb.models.dtos.UserIdDTO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Barry Norton
 *
 */
@Repository
public interface RateArtRepository extends JpaRepository<RateArt, Integer>{

	/**
	 * Retrieve art ratings with the given art Id and author
	 * @param artDtoObj
	 * @param userDtoObj
	 * @return
	 */
	public List<RateArt> findByFanartIdAndAuthor(FanartDTO artDtoObj, UserIdDTO userDtoObj);
	
}
