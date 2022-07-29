package com.revature.pokemondb.repositories;

import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.Fanart;
import com.revature.pokemondb.models.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Barry Norton
 *
 */
@Repository
public interface FanartRepository extends JpaRepository<Fanart, Integer>{
	/**
	 * Returns all fanart objects with the value of isFlagged matching the parameter
	 * @param isFlagged a Boolean to represent whether a fanart is flagged
	 * @return a list of fanart objects
	 */
	public List<Fanart> findByIsFlagged(Boolean isFlagged);

	/**
	 * Returns all fanart objects with the value of reports being greater than the parameter
	 * @param reports an integer to represent how many users have reported a given fanart
	 * @return a list of fanart objects ordered by amount of reports, highest to lowest
	 */
	public List<Fanart> findByReportsGreaterThanOrderByReportsDesc(int reports);	

	/**
	 * Returns all fanart objects with the value of isFlagged matching the parameter
	 * @param isFlagged a Boolean to represent whether a fanart is flagged
	 * @return a list of fanart objects ordered by amount of likes, highest to lowest
	 */
	public List<Fanart> findByIsFlaggedOrderByLikesDesc(Boolean isFlagged);

	/**
	 * Returns all fanart objects with the value of isFlagged matching the parameter
	 * @param isFlagged a Boolean to represent whether a fanart is flagged
	 * @return a list of fanart objects ordered by post date, newest to oldest
	 */
	public List<Fanart> findByIsFlaggedOrderByPostDateDesc(Boolean isFlagged);

	/**
	 * Returns all fanart objects with the value of isFlagged matching the parameter
	 * @param isFlagged a Boolean to represent whether a fanart is flagged
	 * @return a list of fanart objects ordered by post date, oldest to newest
	 */
	public List<Fanart> findByIsFlaggedOrderByPostDate(Boolean isFlagged);
	
	/**
	 * Returns all fanart objects with the value of isFlagged matching the isFlagged parameter
	 * and the value of tags containing the tags parameter
	 * @param isFlagged a Boolean to represent whether a fanart is flagged
	 * @return a list of fanart objects
	 */
	public List<Fanart> findByTagsContainsAndIsFlagged(String tags, Boolean isFlagged);
	
	/**
	 * Returns all fanart objects with the value of isFlagged matching the isFlagged parameter
	 * and the value of title containing the title parameter
	 * @param isFlagged a Boolean to represent whether a fanart is flagged
	 * @return a list of fanart objects
	 */
	public List<Fanart> findByTitleContainsAndIsFlagged(String title, Boolean isFlagged);
	
	/**
	 * Returns all fanart objects with the value of author being equal to the author parameter
	 * @param isFlagged a Boolean to represent whether a fanart is flagged
	 * @return a list of fanart objects
	 */
	public List<Fanart> findByAuthorEquals(User author);
}
