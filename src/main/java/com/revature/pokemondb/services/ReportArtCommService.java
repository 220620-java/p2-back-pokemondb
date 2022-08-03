package com.revature.pokemondb.services;

import com.revature.pokemondb.models.ReportArtComm;
import com.revature.pokemondb.models.dtos.ArtCommDTO;
import com.revature.pokemondb.models.dtos.UserIdDTO;
import com.revature.pokemondb.repositories.ReportArtCommRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author Barry Norton
 * */
@Service
public class ReportArtCommService {
	/*Class Variables*/
	private ReportArtCommRepository reportArtCommRepo;
	
	/*Constructors*/
	
	/**
	 * A Constructor intended to be used for dependency injection by the Spring application
	 * @param commRepo an instance of the ArtCommCommRepository class
	 */
	public ReportArtCommService(ReportArtCommRepository reportArtCommRepo) {
		this.reportArtCommRepo = reportArtCommRepo;
	}
	
	/*Methods*/
	
	/**
	 * Retrieves a user's report of a given fanArtComm. 
	 * Should only be one per user
	 * @param ArtCommId the id of the fanArtComm
	 * @return a list of available comments
	 */
	public ReportArtComm getRatingByUserAndCommentId(int commId, int userId) {
		ArtCommDTO commDtoObj = new ArtCommDTO(commId);
		UserIdDTO userDtoObj = new UserIdDTO(userId);
		List<ReportArtComm> reportArtCommList = reportArtCommRepo.findByCommentIdAndAuthor(commDtoObj, userDtoObj);
		ReportArtComm reportArtComm;
		
		if(!reportArtCommList.equals(new ArrayList<>())) {
			reportArtComm = reportArtCommList.get(0);
		} else {
			reportArtComm = null;
		}
		return reportArtComm;
	}
	
	/**
	 * Saves a given report object to the database
	 * @param comment an object representing a new report to be added to the DB
	 * @return a Boolean representing the success of the operation
	 */
	public Boolean saveReport(ReportArtComm reportArtComm) {
		try {
			reportArtCommRepo.save(reportArtComm);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
