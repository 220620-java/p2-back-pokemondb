package com.revature.pokemondb.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import com.revature.pokemondb.models.ReportArt;
import com.revature.pokemondb.models.dtos.FanartDTO;
import com.revature.pokemondb.models.dtos.UserIdDTO;
import com.revature.pokemondb.repositories.ReportArtRepository;

@SpringBootTest
public class ReportArtServiceTest {
	/*Class Variables*/
    @MockBean
    private ReportArtRepository reportArtRepo;

    @Autowired
    private ReportArtService reportArtService;
    
    /*Testing getRatingByUserAndFanartId()*/
    
    @Test
	public void testGetRatingByUserAndFanartId() {
    	/*Variables*/
    	int mockArtId = 1,
    		mockUserId = 1;
    	FanartDTO mockArt = new FanartDTO(mockArtId);
    	UserIdDTO mockUser = new UserIdDTO(mockUserId, "");
    	List<ReportArt> mockdata = new ArrayList<ReportArt>();
    	ReportArt mockentry = new ReportArt(),
    		expected = new ReportArt(),
    		actual;
    	
    	/*Setup Variables*/
    	mockentry.setId(1);
    	expected.setId(1);
    	mockdata.add(mockentry);
    	
    	/*Mocks*/
    	Mockito.when(reportArtRepo.findByFanartIdAndAuthor(mockArt, mockUser)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = reportArtService.getRatingByUserAndFanartId(mockArtId, mockUserId);
    	
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
    	List<ReportArt> mockdata = new ArrayList<ReportArt>();
    	ReportArt expected = null,
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(reportArtRepo.findByFanartIdAndAuthor(mockArt, mockUser)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = reportArtService.getRatingByUserAndFanartId(mockArtId, mockUserId);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
	}
	
	/*Testing saveRating*/
    
	@Test
	public void testSaveRating() {
    	/*Variables*/
    	ReportArt mockinput = new ReportArt();
    	Boolean expected = true,
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(reportArtRepo.save(mockinput)).thenReturn(mockinput);
    	
    	/*Function*/
    	actual = reportArtService.saveReport(mockinput);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
	}
}
