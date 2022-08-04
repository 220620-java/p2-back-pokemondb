package com.revature.pokemondb.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.pokemondb.PokemondbApplication;
import com.revature.pokemondb.exceptions.EmailAlreadyExistsException;
import com.revature.pokemondb.exceptions.FailedAuthenticationException;
import com.revature.pokemondb.exceptions.UsernameAlreadyExistsException;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.repositories.UserRepository;
import com.revature.pokemondb.utils.SecurityUtils;

@SpringBootTest(classes=PokemondbApplication.class)
public class UserServiceTest {
    
    @MockBean
    private UserRepository userRepo;

    @MockBean
    private User mockUserBean;

    @Autowired
    private UserService userService;

    @Test
    void testGetUserById() {
    
    }
    
    @Test
    void testGetUserByUsername() {
        
    }

    @Test
	void testLoginUser() throws NoSuchAlgorithmException, FailedAuthenticationException {
        // Mock setup
        User mockUser = Mockito.mock(User.class);
        Mockito.when(userRepo.findByUsername("user")).thenReturn(Optional.of(mockUser));
        Mockito.when(mockUser.getUsername()).thenReturn("user");
        Mockito.when(mockUser.getPassword()).thenReturn("pass");
        Mockito.when(mockUser.getSalt()).thenReturn("salt".getBytes());
        SecurityUtils utils = Mockito.mock(SecurityUtils.class);
        Mockito.when(utils.encodePassword("pass", "salt".getBytes())).thenReturn("pass");

        // Call the method to test
        User returnedUser;
        returnedUser = userService.loginUser("user", "pass");

        // Assert that we got a user back
        assertNotNull (returnedUser);
	}
    
    @Test
    void testRegisterUser() throws UsernameAlreadyExistsException, EmailAlreadyExistsException, NoSuchAlgorithmException {
        // Mock setup
        User mockUser = new User();
        User mockUserWithInfo = new User(1l, "colbytang", "ctang@email.com", "pass");
        Mockito.when(userRepo.save(mockUser)).thenReturn(mockUserWithInfo);

        // Call the method to test
        User returnedUser;
        returnedUser = userService.registerUser(mockUser);

        // Assert that we got a user back
        assertNotNull (returnedUser);
    }

    @Test
    void testRegisterUsernameException() throws UsernameAlreadyExistsException {
        // Mock setup
        User mockUser = new User(1l, "colbytang", "ctang@email.com", "pass");
        Mockito.when(userRepo.existsUserByUsername(mockUser.getUsername())).thenReturn(true);

        // Call the method to test
        assertThrows(UsernameAlreadyExistsException.class, 
        () -> userService.registerUser(mockUser));
    }

    @Test
    void testRegisterEmailException() throws EmailAlreadyExistsException {
        // Mock setup
        User mockUser = new User(1l, "colbytang", "ctang@email.com", "pass");
        Mockito.when(userRepo.existsUserByEmail(mockUser.getEmail())).thenReturn(true);

        // Call the method to test
        assertThrows(EmailAlreadyExistsException.class, 
        () -> userService.registerUser(mockUser));
    }

    @Test
    void testRegisterBlankPassword() throws NoSuchAlgorithmException {
        // Mock setup
        mockUserBean = Mockito.mock(User.class);
        SecurityUtils utils = Mockito.mock(SecurityUtils.class);
        User mockUserWithInfo = new User(1l, "colbytang", "ctang@email.com", "pass");
        Mockito.when(mockUserBean.getPassword()).thenReturn("");
        Mockito.when(utils.encodePassword(mockUserBean.getPassword(), "salt".getBytes())).thenReturn("");
        Mockito.when(userRepo.save(mockUserBean)).thenReturn(mockUserWithInfo);

        // Call the method to test
        try {
            assertNotNull(userService.registerUser(mockUserBean));
        } catch (UsernameAlreadyExistsException | EmailAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
