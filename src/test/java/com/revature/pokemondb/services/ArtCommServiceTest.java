package com.revature.pokemondb.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.pokemondb.PokemondbApplication;
import com.revature.pokemondb.models.ArtComment;
import com.revature.pokemondb.models.dtos.FanartDTO;
import com.revature.pokemondb.repositories.ArtCommRepository;

@SpringBootTest(classes=PokemondbApplication.class)
public class ArtCommServiceTest {
	/*Class Variables*/
    @MockBean
    private ArtCommRepository commRepo;

    @Autowired
    private ArtCommService commService;

    /*Testing getAvailableComments()*/
    
	@Test
	public void testGetAvailableFanartCommentsNull() {
		/*Variables*/
		int mockid = 0;
		FanartDTO mockdto = new FanartDTO(mockid);
    	List<ArtComment> mockdata = new ArrayList<ArtComment>(),
    		expected = new ArrayList<ArtComment>(),
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(commRepo.findByFanartIdAndIsFlagged(mockdto, false)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = commService.getAvailibleFanartComments(mockid);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void testGetAvailableFanartComments() {
		/*Variables*/
		int mockid = 0;
		FanartDTO mockdto = new FanartDTO(mockid);
		ArtComment mockentry = new ArtComment();
    	List<ArtComment> mockdata = new ArrayList<ArtComment>(),
    		expected = new ArrayList<ArtComment>(),
    		actual;
    	
    	/*Setup Variables*/
    	mockentry.setId(0);
    	mockdata.add(mockentry);
    	expected.add(mockentry);
    	
    	/*Mocks*/
    	Mockito.when(commRepo.findByFanartIdAndIsFlagged(mockdto, false)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = commService.getAvailibleFanartComments(mockid);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
	}
	
	/*Testing addComment()*/
	
	@Test
	public void testAddComment() {
		/*Variables*/
		ArtComment mockinput = new ArtComment();
		Boolean expected = true,
			actual;
    	
    	/*Setup Variables*/
    	mockinput.setId(0);
    	
    	/*Mocks*/
    	Mockito.when(commRepo.save(mockinput)).thenReturn(mockinput);
    	
    	/*Function*/
    	actual = commService.addComment(mockinput);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
	}

	/*Testing removeComment()*/
	
	@Test
	public void testRemoveComment() {

    	/*Variables*/
    	int mockid = 0;
    	Boolean expected = true,
    		actual;
    	
    	/*Mocks*/
    	Mockito.doNothing().when(commRepo).deleteById(mockid);
    	
    	/*Function*/
    	actual = commService.removeComment(mockid);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
	}
}
