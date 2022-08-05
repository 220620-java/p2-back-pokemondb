package com.revature.pokemondb.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.pokemondb.PokemondbApplication;
import com.revature.pokemondb.controller.AuthController;
import com.revature.pokemondb.controller.UserController;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.repositories.PokemonRepository;

@SpringBootTest(classes=PokemondbApplication.class)
class PokemonServiceImplTest {
    @MockBean
    private AuthController authController;

    @MockBean
    private UserController userController;

    @MockBean
    private UserService userService;

    @MockBean
    private PokemonRepository pokemonRepo;

    @Autowired
    private PokemonService pokemonService;
    
    @Test
    void createPokemon() {
        Pokemon pokemon = pokemonService.createPokemon("pikachu");
        assertNotNull(pokemon);
    }
    
}
