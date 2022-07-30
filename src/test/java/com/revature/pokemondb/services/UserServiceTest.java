package com.revature.pokemondb.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.pokemondb.PokemondbApplication;
import com.revature.pokemondb.exceptions.UsernameAlreadyExistsException;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.repositories.UserRepository;

@SpringBootTest(classes=PokemondbApplication.class)
public class UserServiceTest {
    
    @MockBean
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    @Test
    void testGetUserById() {
    
    }
    
    @Test
    void testGetUserByUsername() {
        
    }
    
    @Test
    void testRegisterUser() throws UsernameAlreadyExistsException {
        // Mock setup
        User mockUser = new User();
        User mockUserWithInfo = new User(1l, "colbytang", "ctang@email.com", "1231231234", "pass");
        Mockito.when(userRepo.save(mockUser)).thenReturn(mockUserWithInfo);

        // Call the method to test
        User returnedUser;
        returnedUser = userService.registerUser(mockUser);

        // Assert that we got a user back
        assertNotNull (returnedUser);
    }

    @Test
    void testLogin() {
        
    }
    
    @Test
    void testBanUser() {

    }

    @Test
    void testUnBanUser() {

    }
}