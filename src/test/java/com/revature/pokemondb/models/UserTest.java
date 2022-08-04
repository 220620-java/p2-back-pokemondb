package com.revature.pokemondb.models;

import static org.junit.jupiter.api.Assertions.*;

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
    void createUser () {
        User user = new User();
        assertNotNull(user);
    }
}
