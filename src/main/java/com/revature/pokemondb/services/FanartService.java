package com.revature.pokemondb.services;

import com.revature.pokemondb.models.Fanart;
import com.revature.pokemondb.repositories.FanartRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * @author Barry Norton
 * */
@Service
public class FanartService {
	/*Class Variables*/
	private FanartRepository artRepo;
	
	/*Constructors*/
	
	/**
	 * A Constructor intended to be used for dependency injection by the Spring application
	 * @param artRepo an Instance of the FanantRepository class
	 */
	public FanartService(FanartRepository artRepo) {
		this.artRepo = artRepo;
	}
	
	
	/**
	 * Retrieves a specific fanart object given its id
	 * @param id the id of the requested fanart object
	 * @return the requested fanart object or null if the id does not exist
	 */
	public Fanart getFanart(int id) {
		Optional<Fanart> artOpt = artRepo.findById(id);
		if (artOpt.isPresent()) {
			return artOpt.get();
		} else {
			return null;
		}
	}
	
	/**
	 * Retrieves all available fanart. Availability is contingent on whether it has been flagged
	 * @return all fanart with the value of 'true' for the isFlagged field
	 */
	public List<Fanart> getAvailableFanart(){
		List<Fanart> fanart = artRepo.findAllByIsFlagged(false);
		return fanart;
	}
	
	/**
	 * Retrieves flagged fanart. Should only be used by admins
	 * @return all fanart with the value of 'false' for the isFlagged field
	 */
	public List<Fanart> getFlaggedFanart(){
		List<Fanart> fanart = artRepo.findAllByIsFlagged(false);
		return fanart;
	}
	
	/**
	 * Retrieves reported fanart. Should only be used by admins
	 * @return all fanart with a value greater than zero for the reports field
	 */
	public List<Fanart> getReportedFanart(){
		List<Fanart> fanart = artRepo.findAllWhereReportsGreaterThanOrderByReportsDesc(0);
		return fanart;
	}
	
	/**
	 * Saves a given fanart object to the database
	 * @param fanart an object representing a new fanart to be added to the DB
	 * @return a Boolean representing the success of the operation
	 */
	public Boolean addFanart(Fanart fanart) {
		try {
			artRepo.save(fanart);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Deletes a given fanart object from the database based on its id. Should only be used by admins. 
	 * Additionally could be made available to the author of the fanart
	 * @param id the id of the fanart object to be removed from the DB
	 * @return a Boolean representing the success of the operation
	 */
	public Boolean removeFanart(int id) {
		try {
			artRepo.deleteById(id);;
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
