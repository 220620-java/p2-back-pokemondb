package com.revature.pokemondb.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import com.revature.pokemondb.models.RateArt;
import com.revature.pokemondb.models.dtos.FanartDTO;
import com.revature.pokemondb.models.dtos.UserIdDTO;
import com.revature.pokemondb.repositories.RateArtRepository;

@SpringBootTest
public class RateArtServiceTest {
	/*Class Variables*/
    @MockBean
    private RateArtRepository rateArtRepo;

    @Autowired
    private RateArtService rateArtService;
    
    /*Testing getRatingByUserAndFanartId()*/
    
    @Test
	public void testGetRatingByUserAndFanartId() {
    	/*Variables*/
    	int mockArtId = 1,
    		mockUserId = 1;
    	FanartDTO mockArt = new FanartDTO(mockArtId);
    	UserIdDTO mockUser = new UserIdDTO(mockUserId, "");
    	List<RateArt> mockdata = new ArrayList<RateArt>();
    	RateArt mockentry = new RateArt(),
    		expected = new RateArt(),
    		actual;
    	
    	/*Setup Variables*/
    	mockentry.setId(1);
    	expected.setId(1);
    	mockdata.add(mockentry);
    	
    	/*Mocks*/
    	Mockito.when(rateArtRepo.findByFanartIdAndAuthor(mockArt, mockUser)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = rateArtService.getRatingByUserAndFanartId(mockArtId, mockUserId);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
	}

    @Test
	public void testGetRatingByUserAndFanartIdNull() {
    	/*Variables*/
    	int mockArtId = 1,
    		mockUserId = 1;
    	FanartDTO mockArt = new FanartDTO(mockArtId);
    	UserIdDTO mockUser = new UserIdDTO(mockUserId, "");
    	List<RateArt> mockdata = new ArrayList<RateArt>();
    	RateArt expected = null,
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(rateArtRepo.findByFanartIdAndAuthor(mockArt, mockUser)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = rateArtService.getRatingByUserAndFanartId(mockArtId, mockUserId);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
	}
	
	/*Testing saveRating*/
    
	@Test
	public void testSaveRating() {
    	/*Variables*/
    	RateArt mockinput = new RateArt();
    	Boolean expected = true,
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(rateArtRepo.save(mockinput)).thenReturn(mockinput);
    	
    	/*Function*/
    	actual = rateArtService.saveRating(mockinput);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
	}
}
