package com.revature.pokemondb.repositories;

import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.RateArtComm;
import com.revature.pokemondb.models.dtos.ArtCommDTO;
import com.revature.pokemondb.models.dtos.UserIdDTO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Barry Norton
 *
 */
@Repository
public interface RateArtCommRepository extends JpaRepository<RateArtComm, Integer>{
	/**
	 * Retrieve comment ratings with the given comment Id and author
	 * @param commDtoObj
	 * @param userDtoObj
	 * @return
	 */
	public List<RateArtComm> findByCommentIdAndAuthor(ArtCommDTO commDtoObj, UserIdDTO userDtoObj);
}
