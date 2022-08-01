package com.revature.pokemondb.services;

import com.revature.pokemondb.models.ArtComment;
import com.revature.pokemondb.models.dtos.FanartDTO;
import com.revature.pokemondb.repositories.ArtCommRepository;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author Barry Norton
 * */
@Service
public class ArtCommService {
	/*Class Variables*/
	private ArtCommRepository commRepo;
	
	/*Constructors*/
	
	/**
	 * A Constructor intended to be used for dependency injection by the Spring application
	 * @param commRepo an instance of the ArtCommRepository class
	 */
	public ArtCommService(ArtCommRepository commRepo) {
		this.commRepo = commRepo;
	}
	
	/*Methods*/
	
	/**
	 * Retrieves all available comments for a given fanart. Availability is contingent on whether it has been flagged
	 * @param artId the id of the fanart
	 * @return a list of available comments
	 */
	public List<ArtComment> getAvailibleFanartComments(int artId) {
		FanartDTO dtoObj = new FanartDTO(artId);
		List<ArtComment> fanart = commRepo.findByFanartIdAndIsFlagged(dtoObj, false);
		return fanart;
	}
	
	/**
	 * Saves a given comment object to the database
	 * @param comment an object representing a new comment to be added to the DB
	 * @return a Boolean representing the success of the operation
	 */
	public Boolean addComment(ArtComment comment) {
		try {
			commRepo.save(comment);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Deletes a given comment object from the database based on its id. Should only be used by admins or the author of the comment
	 * @param id the id of the comment object to be removed from the DB
	 * @return a Boolean representing the success of the operation
	 */
	public Boolean removeComment(int id) {
		try {
			commRepo.deleteById(id);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
