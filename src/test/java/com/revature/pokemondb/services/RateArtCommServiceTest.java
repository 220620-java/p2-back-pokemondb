package com.revature.pokemondb.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import com.revature.pokemondb.models.RateArtComm;
import com.revature.pokemondb.models.dtos.ArtCommDTO;
import com.revature.pokemondb.models.dtos.UserIdDTO;
import com.revature.pokemondb.repositories.RateArtCommRepository;

@SpringBootTest
public class RateArtCommServiceTest {
	/*Class Variables*/
    @MockBean
    private RateArtCommRepository rateCommRepo;

    @Autowired
    private RateArtCommService rateCommService;
    
    /*Testing getRatingByUserAndFanartId()*/
    
    @Test
	public void testGetRatingByUserAndFanartId() {
    	/*Variables*/
    	int mockCommId = 1,
    		mockUserId = 1;
    	ArtCommDTO mockComm = new ArtCommDTO(mockCommId);
    	UserIdDTO mockUser = new UserIdDTO(mockUserId);
    	List<RateArtComm> mockdata = new ArrayList<RateArtComm>();
    	RateArtComm mockentry = new RateArtComm(),
    		expected = new RateArtComm(),
    		actual;
    	
    	/*Setup Variables*/
    	mockentry.setId(1);
    	expected.setId(1);
    	mockdata.add(mockentry);
    	
    	/*Mocks*/
    	Mockito.when(rateCommRepo.findByCommentIdAndAuthor(mockComm, mockUser)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = rateCommService.getRatingByUserAndCommentId(mockCommId, mockUserId);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
	}

    @Test
	public void testGetRatingByUserAndFanartIdNull() {
    	/*Variables*/
    	int mockCommId = 1,
    		mockUserId = 1;
    	ArtCommDTO mockComm = new ArtCommDTO(mockCommId);
    	UserIdDTO mockUser = new UserIdDTO(mockUserId);
    	List<RateArtComm> mockdata = new ArrayList<RateArtComm>();
    	RateArtComm expected = null,
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(rateCommRepo.findByCommentIdAndAuthor(mockComm, mockUser)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = rateCommService.getRatingByUserAndCommentId(mockCommId, mockUserId);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
	}
	
	/*Testing saveRating*/
    
	@Test
	public void testSaveRating() {
    	/*Variables*/
    	RateArtComm mockinput = new RateArtComm();
    	Boolean expected = true,
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(rateCommRepo.save(mockinput)).thenReturn(mockinput);
    	
    	/*Function*/
    	actual = rateCommService.saveRating(mockinput);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
	}
}
