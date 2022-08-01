package com.revature.pokemondb.services;

import com.revature.pokemondb.models.ReportArt;
import com.revature.pokemondb.models.dtos.FanartDTO;
import com.revature.pokemondb.models.dtos.UserIdDTO;
import com.revature.pokemondb.repositories.ReportArtRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author Barry Norton
 * */
@Service
public class ReportArtService {
	/*Class Variables*/
	private ReportArtRepository reportArtRepo;
	
	/*Constructors*/
	
	/**
	 * A Constructor intended to be used for dependency injection by the Spring application
	 * @param commRepo an instance of the ArtCommRepository class
	 */
	public ReportArtService(ReportArtRepository reportArtRepo) {
		this.reportArtRepo = reportArtRepo;
	}
	
	/*Methods*/
	
	/**
	 * Retrieves a user's rating of a given fanart. 
	 * Should only be one per user
	 * @param artId the id of the fanart
	 * @return a list of available comments
	 */
	public ReportArt getRatingByUserAndFanartId(int artId, int userId) {
		FanartDTO artDtoObj = new FanartDTO(artId);
		UserIdDTO userDtoObj = new UserIdDTO(userId);
		List<ReportArt> reportArtList = reportArtRepo.findByFanartIdAndAuthor(artDtoObj, userDtoObj);
		ReportArt reportArt;
		
		if(!reportArtList.equals(new ArrayList<>())) {
			reportArt = reportArtList.get(0);
		} else {
			reportArt = null;
		}
		return reportArt;
	}
	
	/**
	 * Saves a given comment object to the database
	 * @param comment an object representing a new comment to be added to the DB
	 * @return a Boolean representing the success of the operation
	 */
	public Boolean saveRating(ReportArt reportArt) {
		try {
			reportArtRepo.save(reportArt);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
