package com.revature.pokemondb.repositories;

import org.springframework.stereotype.Repository;

import com.revature.pokemondb.models.Fanart;

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
	 * @return a list of fanart objects
	 */
	public List<Fanart> findByReportsGreaterThanOrderByReportsDesc(int reports);
}
