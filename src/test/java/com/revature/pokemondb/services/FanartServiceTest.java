package com.revature.pokemondb.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.pokemondb.models.Fanart;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.repositories.FanartRepository;

@SpringBootTest
public class FanartServiceTest {
	/*Class Variables*/
    @MockBean
    private FanartRepository artRepo;

    @Autowired
    private FanartService artService;
    
    
    /*Testing getLowestId()*/
    
    @Test
    public void testGetLowestIdNull() {
    	/*Variables*/
    	List<Fanart> mockdata = null;
    	int expected = -1,
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByIsFlaggedOrderById(false)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getLowestID();
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }   
    
    @Test
    public void testGetLowestId() {
    	/*Variables*/
    	List<Fanart> mockdata = new ArrayList<Fanart>();
    	Fanart mockentry = new Fanart();
    	int expected = 1,
    		actual;
    	
    	/*Setup Variables*/
    	mockentry.setId(1);
    	mockdata.add(mockentry);
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByIsFlaggedOrderById(false)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getLowestID();
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }    

    /*Testing getHighestId()*/
    
    @Test
    public void testGetHighestIdNull() {
    	/*Variables*/
    	List<Fanart> mockdata = null;
    	int expected = -1,
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByIsFlaggedOrderByIdDesc(false)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getHighestID();
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetHighestId() {
    	/*Variables*/
    	List<Fanart> mockdata = new ArrayList<Fanart>();
    	Fanart mockentry = new Fanart();
    	int expected = 1,
    		actual;
    	
    	/*Setup Variables*/
    	mockentry.setId(1);
    	mockdata.add(mockentry);
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByIsFlaggedOrderByIdDesc(false)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getHighestID();
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }
    
    /*Testing getFanart()*/
    
    @Test
    public void testGetFanartNull() {
    	/*Variables*/
    	int mockid = 1;
    	Optional<Fanart> mockdata = Optional.empty();
    	Fanart expected = null,
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findById(mockid)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getFanart(mockid);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetFanart() {
    	/*Variables*/
    	int mockid = 1;
    	Optional<Fanart> mockdata = Optional.of(new Fanart());
    	Fanart expected = new Fanart(),
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findById(mockid)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getFanart(mockid);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }
    
    /*Testing getAvailibleFanart()*/

    @Test
    public void testGetAvailibleFanartNull() {
    	/*Variables*/
    	List<Fanart> mockdata = new ArrayList<Fanart>(),
    		expected = new ArrayList<Fanart>(),
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByIsFlagged(false)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getAvailableFanart();
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetAvailibleFanart() {
    	/*Variables*/
    	Fanart mockentry = new Fanart();
    	List<Fanart> mockdata = new ArrayList<Fanart>(),
        		expected = new ArrayList<Fanart>(),
        		actual;
    	
    	/*Setup Variables*/
    	mockentry.setId(1);
    	mockdata.add(mockentry);
    	expected.add(mockentry);
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByIsFlagged(false)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getAvailableFanart();
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }
    
    /*Testing getFlaggedFanart()*/
    
    @Test
    public void testGetFlaggedFanartNull() {
    	/*Variables*/
    	List<Fanart> mockdata = new ArrayList<Fanart>(),
    		expected = new ArrayList<Fanart>(),
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByIsFlagged(true)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getFlaggedFanart();
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetFlaggedFanart() {
    	/*Variables*/
    	Fanart mockentry = new Fanart();
    	List<Fanart> mockdata = new ArrayList<Fanart>(),
        		expected = new ArrayList<Fanart>(),
        		actual;
    	
    	/*Setup Variables*/
    	mockentry.setId(1);
    	mockdata.add(mockentry);
    	expected.add(mockentry);
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByIsFlagged(true)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getFlaggedFanart();
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }
    
    /*Testing getReportedFanart()*/
    
    @Test
    public void testGetReportedFanartNull() {
    	/*Variables*/
    	List<Fanart> mockdata = new ArrayList<Fanart>(),
    		expected = new ArrayList<Fanart>(),
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByReportsGreaterThanOrderByReportsDesc(0)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getReportedFanart();
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetReportedFanart() {
    	/*Variables*/
    	Fanart mockentry = new Fanart();
    	List<Fanart> mockdata = new ArrayList<Fanart>(),
        		expected = new ArrayList<Fanart>(),
        		actual;
    	
    	/*Setup Variables*/
    	mockentry.setId(1);
    	mockdata.add(mockentry);
    	expected.add(mockentry);
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByReportsGreaterThanOrderByReportsDesc(0)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getReportedFanart();
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }    

    /*Testing getAvailibleFanartOrderedByLikesDesc()*/

    @Test
    public void testGetAvailibleFanartOrderedByLikesDescNull() {
    	/*Variables*/
    	List<Fanart> mockdata = new ArrayList<Fanart>(),
    		expected = new ArrayList<Fanart>(),
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByIsFlaggedOrderByLikesDesc(false)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getAvailableFanart();
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetAvailibleFanartOrderedByLikesDesc() {
    	/*Variables*/
    	Fanart mockentry = new Fanart();
    	List<Fanart> mockdata = new ArrayList<Fanart>(),
        		expected = new ArrayList<Fanart>(),
        		actual;
    	
    	/*Setup Variables*/
    	mockentry.setId(1);
    	mockdata.add(mockentry);
    	expected.add(mockentry);
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByIsFlaggedOrderByLikesDesc(false)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getAvailableFanartOrderedByLikesDesc();
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }

    /*Testing getAvailibleFanartFilteredByPostDate()*/

    @Test
    public void testGetAvailibleFanartFilterByPostDateGreaterThan() {
    	/*Variables*/
    	String mockDateStr = "2000-01-10";
    	Date mockDate = Date.valueOf("2000-01-10");
    	List<Fanart> mockdata = new ArrayList<Fanart>(),
    		expected = new ArrayList<Fanart>(),
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByIsFlaggedAndPostDateGreaterThanEqual(false, mockDate)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getAvailableFanartFilteredByPostDate(true, mockDateStr);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }
    

    @Test
    public void testGetAvailibleFanartFilteredByPostDateLessThan() {

    	/*Variables*/
    	String mockDateStr = "2000-01-30";
    	Date mockDate = Date.valueOf("2000-01-30");
    	List<Fanart> mockdata = new ArrayList<Fanart>(),
    		expected = new ArrayList<Fanart>(),
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByIsFlaggedAndPostDateLessThanEqual(false, mockDate)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getAvailableFanartFilteredByPostDate(false, mockDateStr);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }
        
    /*Testing getAvailibleFanartWithTags()*/

    @Test
    public void testGetAvailibleFanartWithTagsNull() {
    	/*Variables*/
    	String mocktag = "tag";
    	List<Fanart> mockdata = new ArrayList<Fanart>(),
    		expected = new ArrayList<Fanart>(),
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByTagsContainsAndIsFlagged(mocktag, false)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getAvailableFanart();
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetAvailibleFanartWithTags() {
    	/*Variables*/
    	String mocktag = "tag";
    	Fanart mockentry = new Fanart();
    	List<Fanart> mockdata = new ArrayList<Fanart>(),
        		expected = new ArrayList<Fanart>(),
        		actual;
    	
    	/*Setup Variables*/
    	mockentry.setId(1);
    	mockentry.setTags(mocktag);
    	mockdata.add(mockentry);
    	expected.add(mockentry);
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByTagsContainsAndIsFlagged(mocktag, false)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getAvailableFanartWithTags(mocktag);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    } 
    
    /*Testing getAvailibleFanartWithTitle()*/

    @Test
    public void testGetAvailibleFanartWithTitleNull() {
    	/*Variables*/
    	String mocktitle = "title";
    	List<Fanart> mockdata = new ArrayList<Fanart>(),
    		expected = new ArrayList<Fanart>(),
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByTagsContainsAndIsFlagged(mocktitle, false)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getAvailableFanartWithTitle(mocktitle);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetAvailibleFanartWithTitle() {
    	/*Variables*/
    	String mocktitle = "title";
    	Fanart mockentry = new Fanart();
    	List<Fanart> mockdata = new ArrayList<Fanart>(),
        		expected = new ArrayList<Fanart>(),
        		actual;
    	
    	/*Setup Variables*/
    	mockentry.setId(1);
    	mockentry.setTitle(mocktitle);
    	mockdata.add(mockentry);
    	expected.add(mockentry);
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByTitleContainsAndIsFlagged(mocktitle, false)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getAvailableFanartWithTitle(mocktitle);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }   
    
    /*Testing getFanartByAuthor()*/

    @Test
    public void testGetFanartByAuthorNull() {
    	/*Variables*/
    	User mockuser = new User();
    	List<Fanart> mockdata = new ArrayList<Fanart>(),
    		expected = new ArrayList<Fanart>(),
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByAuthorEquals(mockuser)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getFanartByAuthor(mockuser);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetFanartByAuthor() {
    	/*Variables*/
    	User mockuser = new User();
    	Fanart mockentry = new Fanart();
    	List<Fanart> mockdata = new ArrayList<Fanart>(),
        		expected = new ArrayList<Fanart>(),
        		actual;
    	
    	/*Setup Variables*/
    	mockentry.setId(1);
    	mockdata.add(mockentry);
    	expected.add(mockentry);
    	
    	/*Mocks*/
    	Mockito.when(artRepo.findByAuthorEquals(mockuser)).thenReturn(mockdata);
    	
    	/*Function*/
    	actual = artService.getFanartByAuthor(mockuser);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }
    
    /*Testing getExistsById()*/

    @Test
    public void testGetExistsByIdFalse() {
    	/*Variables*/
    	int mockid = 0;
    	Boolean expected = false,
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(artRepo.existsById(mockid)).thenReturn(false);
    	
    	/*Function*/
    	actual = artService.getExistsById(mockid);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetExistsByIdTrue() {
    	/*Variables*/
    	int mockid = 0;
    	Boolean expected = true,
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(artRepo.existsById(mockid)).thenReturn(true);
    	
    	/*Function*/
    	actual = artService.getExistsById(mockid);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
    } 

    /*Testing addFanart()*/

    @Test
	public void testAddFanart() {
    	/*Variables*/
    	Fanart mockinput = new Fanart();
    	Boolean expected = true,
    		actual;
    	
    	/*Mocks*/
    	Mockito.when(artRepo.save(mockinput)).thenReturn(mockinput);
    	
    	/*Function*/
    	actual = artService.addFanart(mockinput);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
	}

    /*Testing removeFanart()*/

    @Test
	public void testRemoveFanart() {
    	/*Variables*/
    	int mockid = 0;
    	Boolean expected = true,
    		actual;
    	
    	/*Mocks*/
    	Mockito.doNothing().when(artRepo).deleteById(mockid);
    	
    	/*Function*/
    	actual = artService.removeFanart(mockid);
    	
    	/*Test*/
    	Assertions.assertEquals(expected, actual);
	}
}
