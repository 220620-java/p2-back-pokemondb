package com.revature.pokemondb.services;

import com.revature.pokemondb.models.Fanart;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.repositories.FanartRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * @author Barry Norton
 */
@Service
public class FanartService {
	/*Class Variables*/
	private FanartRepository artRepo;
	
	/*Constructors*/
	
	/**
	 * A Constructor intended to be used for dependency injection by the Spring application
	 * @param artRepo an instance of the FanantRepository class
	 */
	public FanartService(FanartRepository artRepo) {
		this.artRepo = artRepo;
	}
	
	/*Methods*/
	
	/**
	 * Retrieves the lowest Id for an available fanart in the database. 
	 * This will be used to determine the lower limit for accessing fanart
	 * @return an id
	 */
	public int getLowestID() {
		List<Fanart> fanart = artRepo.findByIsFlaggedOrderById(false);
		if (fanart != null) {
			return fanart.get(0).getId();
		} else {
			return -1;
		}
	}
	
	/**
	 * Retrieves the highest Id for an available fanart in the database. 
	 * This will be used to determine the upper limit for accessing fanart
	 * @return an id
	 */
	public int getHighestID() {
		List<Fanart> fanart = artRepo.findByIsFlaggedOrderByIdDesc(false);
		if (fanart != null) {
			return fanart.get(0).getId();
		} else {
			return -1;
		}
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
	 * @return all fanart with the value of 'false' for the isFlagged field
	 */
	public List<Fanart> getAvailableFanart(){
		List<Fanart> fanart = artRepo.findByIsFlagged(false);
		return fanart;
	}
	
	/**
	 * Retrieves flagged fanart. Should only be used by admins
	 * @return all fanart with the value of 'true' for the isFlagged field
	 */
	public List<Fanart> getFlaggedFanart(){
		List<Fanart> fanart = artRepo.findByIsFlagged(true);
		return fanart;
	}
	
	/**
	 * Retrieves reported fanart. Should only be used by admins
	 * @return all fanart with a value greater than zero for the reports field
	 */
	public List<Fanart> getReportedFanart(){
		List<Fanart> fanart = artRepo.findByReportsGreaterThanOrderByReportsDesc(0);
		return fanart;
	}
	
	/**
	 * Retrieves all available fanart. Availability is contingent on whether it has been flagged.
	 * Returned fanarts are ordered by likes, highest to lowest
	 * @return all fanart with the value of 'false' for the isFlagged field ordered by the amount of likes
	 */
	public List<Fanart> getAvailableFanartOrderedByLikesDesc(){
		List<Fanart> fanart = artRepo.findByIsFlaggedOrderByLikesDesc(false);
		return fanart;
	}
	
	/**
	 * Retrieves all available fanart. Availability is contingent on whether it has been flagged.
	 * Returned fanarts are ordered by post date
	 * @param greaterThan represents the argument by which the fanarts will be filtered. 'true' for >= and 'false' for <=
	 * @return all fanart with the value of 'false' for the isFlagged field ordered by post date
	 */
	public List<Fanart> getAvailableFanartFilteredByPostDate(Boolean greaterThan, String date){
		List<Fanart> fanart;
		Date postDate = Date.valueOf(date);
		
		//Retrieving data
		if (greaterThan) {
			fanart = artRepo.findByIsFlaggedAndPostDateGreaterThanEqual(false, postDate);
		} else {
			fanart = artRepo.findByIsFlaggedAndPostDateLessThanEqual(false, postDate);
		}
		return fanart;
	}
	
	/**
	 * Retrieves all available fanart with the tags value containing the parameter. Availability is contingent on whether it has been flagged
	 * @return all fanart with the value of 'false' for the isFlagged field and a value containing the parameter for tags
	 */
	public List<Fanart> getAvailableFanartWithTags(String tags){
		List<Fanart> fanart = artRepo.findByTagsContainsAndIsFlagged(tags, false);
		return fanart;
	}
	
	/**
	 * Retrieves all available fanart with the title value containing the parameter. Availability is contingent on whether it has been flagged
	 * @return all fanart with the value of 'false' for the isFlagged field and a value containing the parameter for title
	 */
	public List<Fanart> getAvailableFanartWithTitle(String title){
		List<Fanart> fanart = artRepo.findByTitleContainsAndIsFlagged(title, false);
		return fanart;
	}
	
	/**
	 * Retrieves all fanart posted by a given author. Intended to be used by admin or the author
	 * @return all fanart with the a value containing the parameter for author
	 */
	public List<Fanart> getFanartByAuthor(User author){
		List<Fanart> fanart = artRepo.findByAuthorEquals(author);
		return fanart;
	}
	
	/**
	 * Tests for the existence of a fanart with a given id
	 * @param id the id of the fanart to be found
	 * @return a boolean to represent the fanart's existence
	 */
	public Boolean getExistsById(int id) {
		return artRepo.existsById(id);
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
