package com.revature.pokemondb.services;

import com.revature.pokemondb.models.RateArtComm;
import com.revature.pokemondb.models.dtos.ArtCommDTO;
import com.revature.pokemondb.models.dtos.UserIdDTO;
import com.revature.pokemondb.repositories.RateArtCommRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author Barry Norton
 * */
@Service
public class RateArtCommService {
	/*Class Variables*/
	private RateArtCommRepository rateArtCommRepo;
	
	/*Constructors*/
	
	/**
	 * A Constructor intended to be used for dependency injection by the Spring application
	 * @param rateArtCommRepo an instance of the RateArtCommRepository class
	 */
	public RateArtCommService(RateArtCommRepository rateArtCommRepo) {
		this.rateArtCommRepo = rateArtCommRepo;
	}
	
	/*Methods*/
	
	/**
	 * Retrieves a user's rating of a given fanart comment. 
	 * Should only be one per user
	 * @param commId the id of the fanart comment
	 * @return a list of available comments
	 */
	public RateArtComm getRatingByUserAndCommentId(int commId, int userId) {
		ArtCommDTO commDtoObj = new ArtCommDTO(commId);
		UserIdDTO userDtoObj = new UserIdDTO(userId);
		List<RateArtComm> rateArtCommList = rateArtCommRepo.findByCommentIdAndAuthor(commDtoObj, userDtoObj);
		RateArtComm rateArtComm;
		
		if(!rateArtCommList.equals(new ArrayList<>())) {
			rateArtComm = rateArtCommList.get(0);
		} else {
			rateArtComm = null;
		}
		return rateArtComm;
	}
	
	/**
	 * Saves a given comment object to the database
	 * @param comment an object representing a new comment to be added to the DB
	 * @return a Boolean representing the success of the operation
	 */
	public Boolean saveRating(RateArtComm rateArtComm) {
		try {
			rateArtCommRepo.save(rateArtComm);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
