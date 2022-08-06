package com.revature.pokemondb.services;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
    void createPokemonByName() {
        // Pokemon pokemon = pokemonService.createPokemon("pikachu");
        Pokemon mockPokemon = new Pokemon();
        Mockito.when(pokemonRepo.save(mockPokemon)).thenReturn(mockPokemon);
        Pokemon pokemon = pokemonService.createPokemon("pikachu");
        assertNotNull(pokemon);
    }

    @Test
    void createPokemonById () {
        Pokemon mockPokemon = new Pokemon();
        Mockito.when(pokemonRepo.save(mockPokemon)).thenReturn(mockPokemon);
        Pokemon pokemon = pokemonService.createPokemon(1);
        assertNotNull(pokemon);
    }

    @Test 
    void getReferencePokemonById () {
        Pokemon mockPokemon = new Pokemon();
        Mockito.when(pokemonRepo.findById(1)).thenReturn(Optional.of(mockPokemon));
        Pokemon pokemon = pokemonService.getReferencePokemon(1);
        assertNotNull(pokemon);
    }

    @Test 
    void getReferencePokemonByIdNull () {
        Mockito.when(pokemonRepo.findById(1)).thenReturn(Optional.empty());
        Pokemon pokemon = pokemonService.getReferencePokemon(1);
        assertNull(pokemon);
    }

    @Test 
    void getReferencePokemonByName () {
        Pokemon mockPokemon = new Pokemon();
        Mockito.when(pokemonRepo.findByName("pikachu")).thenReturn(Optional.of(mockPokemon));
        Pokemon pokemon = pokemonService.getReferencePokemon("pikachu");
        assertNotNull(pokemon);
    }

    @Test 
    void getReferencePokemonByNameNull () {
        Mockito.when(pokemonRepo.findByName("pikachu")).thenReturn(Optional.empty());
        Pokemon pokemon = pokemonService.getReferencePokemon("pikachu");
        assertNull(pokemon);
    }

    @Test
    void getAllPokemonById () {
        List<Integer> ids = Arrays.asList(1,2,3,4,5);
        Pokemon mockPokemon = new Pokemon();
        Mockito.when(pokemonRepo.findById(1)).thenReturn(Optional.of(mockPokemon));
        Mockito.when(pokemonRepo.findById(2)).thenReturn(Optional.of(mockPokemon));
        Mockito.when(pokemonRepo.findById(3)).thenReturn(Optional.of(mockPokemon));
        Mockito.when(pokemonRepo.findById(4)).thenReturn(Optional.of(mockPokemon));
        Mockito.when(pokemonRepo.findById(5)).thenReturn(Optional.of(mockPokemon));
        List<Pokemon> pokemonList = pokemonService.getAllPokemonById(ids);
        assertTrue (!pokemonList.isEmpty());
    }
    
    private String getStringFromFile (String path) {
		BufferedReader reader = null; 
        String retString = "";
		try {
			reader = new BufferedReader(new FileReader(path));
			for (String line; (line = reader.readLine()) != null;) {
				retString += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) try { reader.close(); } catch (IOException ignore) {}
		}
        return retString;
	}
}
