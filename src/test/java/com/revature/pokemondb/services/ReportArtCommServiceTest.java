package com.revature.pokemondb.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import com.revature.pokemondb.models.ReportArtComm;
import com.revature.pokemondb.models.dtos.ArtCommDTO;
import com.revature.pokemondb.models.dtos.UserIdDTO;
import com.revature.pokemondb.repositories.ReportArtCommRepository;

@SpringBootTest
public class ReportArtCommServiceTest {
	/*Class Variables*/
    @MockBean
    private ReportArtCommRepository reportCommRepo;

    @Autowired
    private ReportArtCommService reportCommService;
    
    /*Testing getRatingByUserAndFanartId()*/
    
    @Test
	public void testGetRatingByUserAndFanartId() {
    	/*Variables*/
    	int mockCommId = 1,
    		mockUserId = 1;
    	ArtCommDTO mockComm = new ArtCommDTO(mockCommId);
    	UserIdDTO mockUser = new UserIdDTO(mockUserId, "");
    	List<ReportArtComm> mockdata = new ArrayList<ReportArtComm>();
    	ReportArtComm mockentry = new ReportArtComm(),
    		expected = new ReportArtComm(),
    		actual;
    	
    	/*Setup Variables*/
    	mockentry.setId(1);
    	expected.setId(1);
    	mockdata.add(mockentry);
    	
    	/*Mocks*/
    	Mockito.when(reportCommRepo.findByCommentIdAndAuthor(mockComm, mockUser)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = reportCommService.getRatingByUserAndCommentId(mockCommId, mockUserId);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
	}

    @Test
	public void testGetRatingByUserAndFanartIdNull() {
    	/*Variables*/
    	int mockCommId = 1,
    		mockUserId = 1;
    	ArtCommDTO mockComm = new ArtCommDTO(mockCommId);
    	UserIdDTO mockUser = new UserIdDTO(mockUserId, "");
    	List<ReportArtComm> mockdata = new ArrayList<ReportArtComm>();
    	ReportArtComm expected = null,
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(reportCommRepo.findByCommentIdAndAuthor(mockComm, mockUser)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = reportCommService.getRatingByUserAndCommentId(mockCommId, mockUserId);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
	}
	
	/*Testing saveRating*/
    
	@Test
	public void testSaveRating() {
    	/*Variables*/
    	ReportArtComm mockinput = new ReportArtComm();
    	Boolean expected = true,
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(reportCommRepo.save(mockinput)).thenReturn(mockinput);
    	
    	/*Function*/
    	actual = reportCommService.saveReport(mockinput);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
	}
}
