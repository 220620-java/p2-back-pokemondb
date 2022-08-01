package com.revature.pokemondb.repositories;

import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.ReportArt;
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
public interface ReportArtRepository extends JpaRepository<ReportArt, Integer>{

	/**
	 * Retrieves the rating associated with
	 * @param artDtoObj
	 * @param userDtoObj
	 * @return
	 */
	public List<ReportArt> findByFanartIdAndUserId(FanartDTO artDtoObj, UserIdDTO userDtoObj);
}
