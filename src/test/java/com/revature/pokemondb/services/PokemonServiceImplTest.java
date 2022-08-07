package com.revature.pokemondb.services;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.pokemondb.controller.AuthController;
import com.revature.pokemondb.controller.UserController;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.models.dtos.PokemonDTO;
import com.revature.pokemondb.repositories.PokemonRepository;


@SpringBootTest
class PokemonServiceImplTest {
    // @MockBean
    // private AuthController authController;

    // @MockBean
    // private UserController userController;

    // @MockBean
    // private UserService userService;

    @MockBean
    private WebClientService webClient;

    @MockBean
    private PokemonRepository pokemonRepo;

    @Autowired
    private PokemonService pokemonService;

    private String pikachuJSON;
    private String speciesJSON;
    private String evolutionJSON;
    private String locationJSON;

    @Test
    void createPokemonByNameAndId() {
        try {
            pikachuJSON = getStringFromFile("src\\test\\resources\\pikachu_pokemon.json");
            speciesJSON = getStringFromFile("src\\test\\resources\\pikachu_species.json");
            evolutionJSON = getStringFromFile("src\\test\\resources\\pikachu_evolution.json");
            locationJSON = getStringFromFile("src\\test\\resources\\pikachu_location.json");
            PokemonDTO dto = new PokemonDTO();
            Mockito.when(webClient.getRequestJSON("https://pokeapi.co/api/v2/pokemon/pikachu")).thenReturn(pikachuJSON);
            Mockito.when(webClient.getRequestJSON("https://pokeapi.co/api/v2/pokemon-species/25")).thenReturn(speciesJSON);
            Mockito.when(webClient.getRequestJSON("https://pokeapi.co/api/v2/evolution-chain/10/")).thenReturn(evolutionJSON);
            Mockito.when(webClient.getRequestJSON("https://pokeapi.co/api/v2/pokemon/25/encounters")).thenReturn(locationJSON);
            Mockito.when(pokemonRepo.save(dto)).thenReturn(dto);
            Pokemon pokemon = pokemonService.createPokemon("pikachu");
            assertNotNull(pokemon);
    
            Mockito.when(webClient.getRequestJSON("https://pokeapi.co/api/v2/pokemon/25")).thenReturn(pikachuJSON);
            Pokemon pokemonId = pokemonService.createPokemon(25);
            assertNotNull(pokemonId);
        } catch (IOException e) {
            e.printStackTrace();
        }     
    }

    @Test 
    void getReferencePokemonById () {
        PokemonDTO mockPokemon = new PokemonDTO();
        Mockito.when(pokemonRepo.findById(1)).thenReturn(Optional.of(mockPokemon));
        PokemonDTO pokemon = pokemonService.getReferencePokemon(1);
        assertNotNull(pokemon);
    }

    @Test 
    void getReferencePokemonByIdNull () {
        Mockito.when(pokemonRepo.findById(1)).thenReturn(Optional.empty());
        PokemonDTO pokemon = pokemonService.getReferencePokemon(1);
        assertNull(pokemon);
    }

    @Test 
    void getReferencePokemonByName () {
        PokemonDTO mockPokemon = new PokemonDTO();
        Mockito.when(pokemonRepo.findByName("pikachu")).thenReturn(Optional.of(mockPokemon));
        PokemonDTO pokemon = pokemonService.getReferencePokemon("pikachu");
        assertNotNull(pokemon);
    }

    @Test 
    void getReferencePokemonByNameNull () {
        Mockito.when(pokemonRepo.findByName("pikachu")).thenReturn(Optional.empty());
        PokemonDTO pokemon = pokemonService.getReferencePokemon("pikachu");
        assertNull(pokemon);
    }

    @Test
    void getAllPokemonById () {
        List<Integer> ids = Arrays.asList(1,2,3,4,5);
        PokemonDTO mockPokemon = new PokemonDTO();
        Mockito.when(pokemonRepo.findById(1)).thenReturn(Optional.of(mockPokemon));
        Mockito.when(pokemonRepo.findById(2)).thenReturn(Optional.of(mockPokemon));
        Mockito.when(pokemonRepo.findById(3)).thenReturn(Optional.of(mockPokemon));
        Mockito.when(pokemonRepo.findById(4)).thenReturn(Optional.of(mockPokemon));
        Mockito.when(pokemonRepo.findById(5)).thenReturn(Optional.of(mockPokemon));
        List<PokemonDTO> pokemonList = pokemonService.getAllPokemonById(ids);
        assertTrue (!pokemonList.isEmpty());
    }

    private String getStringFromFile (String path) throws IOException {
		BufferedReader reader = null; 
        String retString = "";
		try {
			reader = new BufferedReader(new FileReader(path));
			for (String line; (line = reader.readLine()) != null;) {
				retString += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
            throw e;
		} finally {
			if (reader != null) try { reader.close(); } catch (IOException ignore) {}
		}
        return retString;
	}
}
