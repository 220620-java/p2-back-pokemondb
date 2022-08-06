package com.revature.pokemondb.services;

import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.pokemondb.PokemondbApplication;
import com.revature.pokemondb.exceptions.EmailAlreadyExistsException;
import com.revature.pokemondb.exceptions.FailedAuthenticationException;
import com.revature.pokemondb.exceptions.InvalidInputException;
import com.revature.pokemondb.exceptions.RecordNotFoundException;
import com.revature.pokemondb.exceptions.UsernameAlreadyExistsException;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.repositories.UserRepository;
import com.revature.pokemondb.utils.SecurityUtils;

@SpringBootTest(classes=PokemondbApplication.class)
class UserServiceTest {
    
    @MockBean
    private UserRepository userRepo;

    @MockBean
    private SecurityUtils mockUtils;

    @Autowired
    private UserService userService;

    /**
     * Grabs all the users from the database and returns it.
     */
    @Test
    void testGetAllUsers() {
        Mockito.when(userRepo.findAll()).thenReturn(new ArrayList<User>());
        assertNotNull(userService.getAllUsers());
    }

    /**
     * Tests retrieving a user by id, if user has been found then return the user.
     */
    @Test
    void testGetUserById() throws RecordNotFoundException {
        User mockUser = new User();
        Mockito.when(userRepo.findById(1l)).thenReturn(Optional.of(mockUser));

        assertNotNull (userService.getUserById(1l));
    }

    /**
     * Tests if a user is not found by id in the database and throws a
     * RecordNotFoundException.
     */
    @Test
    void testGetUserByIdNotFound() throws RecordNotFoundException {
        Mockito.when(userRepo.findById(1l)).thenReturn(Optional.empty());

        assertThrows (RecordNotFoundException.class, 
        () -> userService.getUserById(1l));
    }
    
    /**
     * Tests retrieving a user by username, if user has been found then
     * return the user.
     */
    @Test
    void testGetUserByUsername() throws RecordNotFoundException {
        User mockUser = new User();
        Mockito.when(userRepo.findByUsername("user")).thenReturn(Optional.of(mockUser));

        assertNotNull (userService.getUserByUsername("user"));
    }

    /**
     * Tests if user is not found by username in the database and throws a RecordNotFoundException.
     */
    @Test
    void testGetUserByUsernameNotFound() throws RecordNotFoundException {
        Mockito.when(userRepo.findByUsername("user")).thenReturn(Optional.empty());

        assertThrows (RecordNotFoundException.class, 
        () -> userService.getUserByUsername("user"));
    }

    /**
     * Tests logging the user in if they have correct credentials.
     * Username must exist and password must match.
     * @throws NoSuchAlgorithmException
     * @throws FailedAuthenticationException
     * @throws RecordNotFoundException
     */
    @Test
	void testLoginUserSuccess() throws NoSuchAlgorithmException, FailedAuthenticationException, RecordNotFoundException {
        // Mock setup
        User mockUser = new User();
        mockUser.setUsername("user");
        mockUser.setPassword("pass");
        mockUser.setSalt("salt".getBytes());
        Mockito.when(userRepo.findByUsername("user")).thenReturn(Optional.of(mockUser));
        Mockito.when(mockUtils.encodePassword("pass", "salt".getBytes())).thenReturn("pass");

        // Call the method to test
        User returnedUser;
        returnedUser = userService.loginUser("user", "pass");

        // Assert that we got a user back
        assertNotNull (returnedUser);
	}

    /**
     * Tests logging the user in if they have a wrong password.
     * Should throw a FailedAuthenticationException.
     * @throws NoSuchAlgorithmException
     * @throws FailedAuthenticationException
     */
    @Test
	void testLoginUserFailAuth() throws NoSuchAlgorithmException, FailedAuthenticationException {
        // Mock setup
        User mockUser = new User();
        mockUser.setUsername("user");
        mockUser.setPassword("mockPass");
        mockUser.setSalt("salt".getBytes());
        Mockito.when(userRepo.findByUsername("user")).thenReturn(Optional.of(mockUser));
        Mockito.when(mockUtils.encodePassword("pass", "salt".getBytes())).thenReturn("pass");

        // Should throw a failed authentication
        assertThrows(FailedAuthenticationException.class,
        () -> userService.loginUser("user", "pass"));
	}

    /**
     * Tests logging the user in and not being able to find the user.
     * Should throw FailedAuthenticationException.
     * @throws NoSuchAlgorithmException
     * @throws FailedAuthenticationException
     */
    @Test
	void testLoginUserCannotFindUser() throws NoSuchAlgorithmException, FailedAuthenticationException {
        // Mock setup
        Mockito.when(userRepo.findByUsername("user")).thenReturn(Optional.empty());
        
        // Should throw a RecordNotFoundException
        assertThrows(RecordNotFoundException.class,
        () -> userService.loginUser("user", "pass"));
	}

    @Test
    void testLoginWrongSaltingAlgorithm() throws NoSuchAlgorithmException {
        User mockUser = new User();
        mockUser.setUsername("username");
        mockUser.setPassword("mockPass");
        mockUser.setSalt("salt".getBytes());

        Mockito.when(userRepo.findByUsername("username")).thenReturn(Optional.of(mockUser));
        Mockito.when(mockUtils.encodePassword("password", "salt".getBytes())).thenThrow(NoSuchAlgorithmException.class);
        assertThrows (NoSuchAlgorithmException.class,
        () -> userService.loginUser("username", "password"));
    }
    
    /**
     * Tests registering a user with username, email, password.
     * Generates a salt and hashes the password.
     * Returns the saved user.
     * @throws UsernameAlreadyExistsException
     * @throws EmailAlreadyExistsException
     * @throws NoSuchAlgorithmException
     * @throws InvalidInputException
     */
    @Test
    void testRegisterUser() throws UsernameAlreadyExistsException, EmailAlreadyExistsException, NoSuchAlgorithmException, InvalidInputException {
        // Mock setup
        User mockUser = new User();
        mockUser.setUsername("colbytang");
        mockUser.setPassword("pass");
        mockUser.setEmail("ctang@email.com");
        User mockUserWithInfo = new User("colbytang", "ctang@email.com", "pass");
        Mockito.when(userRepo.save(mockUser)).thenReturn(mockUserWithInfo);

        // Call the method to test
        User returnedUser;
        returnedUser = userService.registerUser(mockUser);

        // Assert that we got a user back
        assertNotNull (returnedUser);
    }

    /**
     * Tests registering a user if it already exists in the database.
     * Should throw UsernameAlreadyExistsException.
     * @throws UsernameAlreadyExistsException
     */
    @Test
    void testRegisterUsernameException() throws UsernameAlreadyExistsException {
        // Mock setup
        User mockUser = new User(1l, "colbytang", "ctang@email.com", "pass");
        Mockito.when(userRepo.existsUserByUsername(mockUser.getUsername())).thenReturn(true);

        // Call the method to test
        assertThrows(UsernameAlreadyExistsException.class, 
        () -> userService.registerUser(mockUser));
    }

    /**
     * Tests registering a user with an email that already exists in the database.
     * Should throw EmailAlreadyExistsException.
     * @throws EmailAlreadyExistsException
     */
    @Test
    void testRegisterEmailException() throws EmailAlreadyExistsException {
        // Mock setup
        User mockUser = new User(1l, "colbytang", "ctang@email.com", "pass");
        Mockito.when(userRepo.existsUserByEmail(mockUser.getEmail())).thenReturn(true);

        // Call the method to test
        assertThrows(EmailAlreadyExistsException.class, 
        () -> userService.registerUser(mockUser));
    }

    /**
     * Tests registering a user with a blank password.
     * Should fail.
     * @throws NoSuchAlgorithmException
     */
    @Test
    void testRegisterBlankPassword() throws NoSuchAlgorithmException {
        // Mock setup
        User mockUser = new User();
        mockUser.setPassword("");
        User mockUserWithInfo = new User(1l, "colbytang", "ctang@email.com", "pass");
        // Mockito.when(mockUtils.encodePassword(mockUser.getPassword(), "salt".getBytes())).thenReturn("");
        Mockito.when(userRepo.save(mockUser)).thenReturn(mockUserWithInfo);

        // Input should be invalid
        assertThrows(InvalidInputException.class, () -> userService.registerUser(mockUser));
    }

    /**
     * If the security utils object is configured incorrectly
     * it should throw a NoSuchAlgorithm exception.
     * @throws NoSuchAlgorithmException
     */
    @Test
    void testRegisterWrongSaltingAlgorithm() throws NoSuchAlgorithmException {
        User mockUser = new User();
        mockUser.setUsername("username");
        mockUser.setPassword("password");
        mockUser.setSalt("salt".getBytes());

        Mockito.when(userRepo.findByUsername(mockUser.getUsername())).thenReturn(Optional.of(mockUser));
        Mockito.when(mockUtils.generateSalt()).thenReturn("salt".getBytes());
        Mockito.when(mockUtils.encodePassword(mockUser.getPassword(), "salt".getBytes())).thenThrow(NoSuchAlgorithmException.class);
        assertThrows (NoSuchAlgorithmException.class,
        () -> userService.registerUser(mockUser));
    }

    /**
     * Update the user and return it.
     * @throws RecordNotFoundException
     */
    @Test
    void testUpdateUser() throws RecordNotFoundException {
        User mockUser = new User();
        Mockito.when(userRepo.existsUserByUsername(mockUser.getUsername())).thenReturn(true);
        assertNotNull(userService.updateUser(mockUser));
    }

    /**
     * Try updating the user and they don't exist in the database.
     * Should throw a RecordNotFoundException
     * @throws RecordNotFoundException
     */
    @Test
    void testUpdateUserNotFound() throws RecordNotFoundException {
        User mockUser = new User();
        Mockito.when(userRepo.existsUserByUsername(mockUser.getUsername())).thenReturn(false);
        assertThrows(RecordNotFoundException.class, 
        () -> userService.updateUser(mockUser));
    }

    /**
     * Delete the user and return the user deleted.
     * @throws RecordNotFoundException
     */
    @Test
    void testDeleteUser() throws RecordNotFoundException {
        User mockUser = new User();
        Mockito.when(userRepo.existsUserByUsername(mockUser.getUsername())).thenReturn(true);
        assertNotNull(userService.deleteUser(mockUser));
    }

    /**
     * Try deleting the user but they cannot be found in the database.
     * Should throw a RecordNotFoundException
     * @throws RecordNotFoundException
     */
    @Test
    void testDeleteUserNotFound() throws RecordNotFoundException {
        User mockUser = new User();
        Mockito.when(userRepo.existsUserByUsername(mockUser.getUsername())).thenReturn(false);
        assertThrows(RecordNotFoundException.class, 
        () -> userService.deleteUser(mockUser));
    }

    /**
     * Inserts the user into the ban table to indicate
	 * a user has been banned.
     */
    @Test
    void banUser() {
        User mockUser = new User();
        assertNotNull (userService.banUser(mockUser));
    }
    
    // TODO Make a ban test case for when the user doesn't exist

    /**
     * Removes a user from the ban table to indicate
	 * a user has been unbanned.
     */
    @Test
    void unBanUser() {
        User mockUser = new User();
        assertNotNull (userService.unBanUser(mockUser));
    }

    // TODO Make an unban test case for when the user doesn't exist
}
