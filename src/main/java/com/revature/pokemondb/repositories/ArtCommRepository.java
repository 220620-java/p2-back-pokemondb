package com.revature.pokemondb.repositories;

import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.ArtComment;
import com.revature.pokemondb.models.dtos.FanartDTO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Barry Norton
 *
 */
@Repository
public interface ArtCommRepository extends JpaRepository<ArtComment, Integer>{
	/**
	 * Returns all comments that are associated with a given fanart and have a value for isFlagged to match the parameter.
	 * @param artId the id of the fanart
	 * @param isFlagged a Boolean to represent the comment's flagged status
	 * @return a list of comments
	 */
	public List<ArtComment> findByFanartIdAndIsFlagged(FanartDTO artId, Boolean isFlagged);
}
