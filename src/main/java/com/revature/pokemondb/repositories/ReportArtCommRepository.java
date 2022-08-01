package com.revature.pokemondb.repositories;

import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.ReportArtComm;
import com.revature.pokemondb.models.dtos.ArtCommDTO;
import com.revature.pokemondb.models.dtos.UserIdDTO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author
 *
 */
@Repository
public interface ReportArtCommRepository extends JpaRepository<ReportArtComm, Integer>{
	/**
	 * Retrieve comment ratings with the given comment Id and author
	 * @param commDtoObj
	 * @param userDtoObj
	 * @return
	 */
	List<ReportArtComm> findByCommentIdAndAuthor(ArtCommDTO commDtoObj, UserIdDTO userDtoObj);
	
}
