package com.revature.pokemondb.services;

import com.revature.pokemondb.models.RateArt;
import com.revature.pokemondb.models.dtos.FanartDTO;
import com.revature.pokemondb.models.dtos.UserIdDTO;
import com.revature.pokemondb.repositories.RateArtRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author Barry Norton
 * */
@Service
public class RateArtService {
	/*Class Variables*/
	private RateArtRepository rateArtRepo;
	
	/*Constructors*/
	
	/**
	 * A Constructor intended to be used for dependency injection by the Spring application
	 * @param rateArtRepo an instance of the RateArtRepository class
	 */
	public RateArtService(RateArtRepository rateArtRepo) {
		this.rateArtRepo = rateArtRepo;
	}
	
	/*Methods*/
	
	/**
	 * Retrieves a user's rating of a given fanart. 
	 * Should only be one per user
	 * @param artId the id of the fanart
	 * @return a list of available comments
	 */
	public RateArt getRatingByUserAndFanartId(int artId, int userId) {
		FanartDTO artDtoObj = new FanartDTO(artId);
		UserIdDTO userDtoObj = new UserIdDTO(userId);
		List<RateArt> rateArtList = rateArtRepo.findByFanartIdAndAuthor(artDtoObj, userDtoObj);
		RateArt rateArt;
		
		if(!rateArtList.equals(new ArrayList<>())) {
			rateArt = rateArtList.get(0);
		} else {
			rateArt = null;
		}
		return rateArt;
	}
	
	/**
	 * Saves a given comment object to the database
	 * @param comment an object representing a new comment to be added to the DB
	 * @return a Boolean representing the success of the operation
	 */
	public Boolean saveRating(RateArt rateArt) {
		try {
			rateArtRepo.save(rateArt);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
