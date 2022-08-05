package com.revature.pokemondb.models;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.pokemondb.PokemondbApplication;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.models.dtos.UserDTO;

@SpringBootTest(classes=PokemondbApplication.class)
public class UserTest {
    
    @Test
    void createDefaultUser () {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    void createIdUser () {
        Long expectedId = 1l;
        User user = new User(expectedId);
        assertNotNull(user);
        assertEquals(user.getUserId(), expectedId);
    }

    @Test
    void createUsernameEmailUser () {
        String expectedUsername = "username";
        String expectedEmail = "email";
        User user = new User(expectedUsername, expectedEmail);
        assertNotNull(user);
        assertEquals(user.getUsername(), expectedUsername);
        assertEquals(user.getEmail(), expectedEmail);
    }

    @Test
    void createUsernameEmailPasswordUser () {
        String expectedUsername = "username";
        String expectedEmail = "email";
        String expectedPassword = "pass";
        User user = new User(expectedUsername, expectedEmail, expectedPassword);
        assertNotNull(user);
        assertEquals(user.getUsername(), expectedUsername);
        assertEquals(user.getEmail(), expectedEmail);
        assertEquals(user.getPassword(), expectedPassword);
    }

    @Test
    void createIdUsernameEmailPasswordUser () {
        Long expectedId = 1l;
        String expectedUsername = "username";
        String expectedEmail = "email";
        String expectedPassword = "pass";
        User user = new User(expectedId, expectedUsername, expectedEmail, expectedPassword);
        assertNotNull(user);
        assertEquals(user.getUserId(), expectedId);
        assertEquals(user.getUsername(), expectedUsername);
        assertEquals(user.getEmail(), expectedEmail);
        assertEquals(user.getPassword(), expectedPassword);
    }

    @Test
    void createFullUser () {
        Long expectedId = 1l;
        String expectedUsername = "username";
        String expectedEmail = "email";
        String expectedPassword = "pass";
        byte[] expectedSalt = "pass".getBytes();
        User user = new User(expectedId, expectedUsername, expectedEmail, expectedPassword, expectedSalt);
        assertNotNull(user);
        assertEquals(user.getUserId(), expectedId);
        assertEquals(user.getUsername(), expectedUsername);
        assertEquals(user.getEmail(), expectedEmail);
        assertEquals(user.getPassword(), expectedPassword);
        assertEquals(user.getSalt(), expectedSalt);
    }

    @Test
    void createUserFromuser () {
        Long expectedId = 1l;
        String expectedUsername = "username";
        String expectedEmail = "email";
        String expectedPassword = "pass";
        byte[] expectedSalt = "pass".getBytes();
        String expectedRole = "user";
        User user = new User(expectedId, expectedUsername, expectedEmail, expectedPassword, expectedSalt);
        user.setRole(expectedRole);
        assertNotNull(user);
        assertEquals(user.getUserId(), expectedId);
        assertEquals(user.getUsername(), expectedUsername);
        assertEquals(user.getEmail(), expectedEmail);
        assertEquals(user.getPassword(), expectedPassword);
        assertEquals(user.getSalt(), expectedSalt);
        assertEquals(user.getRole(), expectedRole);
    }

    @Test
    void createUserFromDTO () {
        Long expectedId = 1l;
        String expectedUsername = "username";
        String expectedEmail = "email";
        String expectedRole = "user";
        UserDTO userDTO = new UserDTO(expectedId, expectedUsername, expectedEmail);
        User user = new User(userDTO);
        user.setRole(expectedRole);
        assertNotNull(user);
        assertEquals(user.getUserId(), expectedId);
        assertEquals(user.getUsername(), expectedUsername);
        assertEquals(user.getEmail(), expectedEmail);
        assertEquals(user.getRole(), expectedRole);
    }

    @Test
    void createUserFromMap () {
        Map<String, String> userMap = new HashMap<>();

        Long expectedId = 1l;
        userMap.put("userId", String.valueOf(expectedId));

        String expectedUsername = "username";
        userMap.put("username", expectedUsername);

        String expectedEmail = "email";
        userMap.put("email", expectedEmail);

        String expectedPassword = "pass";
        userMap.put("password", expectedPassword);

        byte[] expectedSalt = "pass".getBytes(StandardCharsets.UTF_8);
        userMap.put("salt", new String(expectedSalt, StandardCharsets.UTF_8));

        String expectedRole = "user";
        userMap.put("role", expectedRole);

        User user = new User(userMap);
        assertNotNull(user);
        assertEquals(user.getUserId(), expectedId);
        assertEquals(user.getUsername(), expectedUsername);
        assertEquals(user.getEmail(), expectedEmail);
        assertEquals(user.getPassword(), expectedPassword);
        assertEquals(new String(user.getSalt(), StandardCharsets.UTF_8), new String(expectedSalt, StandardCharsets.UTF_8));
        assertEquals(user.getRole(), expectedRole);
    }

    @Test
    void userHashCodeTest () {
        Long expectedId = 1l;
        String expectedUsername = "username";
        String expectedEmail = "email";
        String expectedPassword = "pass";
        User user = new User(expectedId, expectedUsername, expectedEmail, expectedPassword);
        User user2 = new User(expectedId, expectedUsername, expectedEmail, expectedPassword);
        assertEquals(user.hashCode(), user2.hashCode());
    }

    @Test
    void userEquals () {
        Long expectedId = 1l;
        String expectedUsername = "username";
        String expectedEmail = "email";
        String expectedPassword = "pass";
        User user = new User(expectedId, expectedUsername, expectedEmail, expectedPassword);
        User user2 = new User(expectedId, expectedUsername, expectedEmail, expectedPassword);
        assertEquals(user, user2);
    }
}
