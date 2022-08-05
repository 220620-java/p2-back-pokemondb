package com.revature.pokemondb.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.PokemondbApplication;
import com.revature.pokemondb.controller.AuthController;
import com.revature.pokemondb.controller.UserController;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.repositories.PokemonRepository;

import reactor.core.publisher.Mono;

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

    // @MockBean
    // private WebClient webClient;

    // @Mock
	// private WebClient.RequestBodyUriSpec requestBodyUriSpecMock;

	// @Mock
	// private WebClient.RequestBodySpec requestBodySpecMock;

    // @SuppressWarnings("rawtypes")
	// @Mock
	// private WebClient.RequestHeadersSpec requestHeadersSpecMock;

    // @SuppressWarnings("rawtypes")
	// @Mock
	// private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;	

	// @Mock
	// private WebClient.ResponseSpec responseSpecMock;

    @Autowired
    private PokemonService pokemonService;

    @SuppressWarnings("unchecked")
    @Test
    void createPokemonByName() {
        // Pokemon pokemon = pokemonService.createPokemon("pikachu");
        Pokemon mockPokemon = new Pokemon();
        
        // // Pokemon API Request
        // String pikachuJSON = getStringFromFile("src\\test\\resources\\pikachu_pokemon.json");
        // Mockito.when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
        // Mockito.when(webClient.get().uri("https://pokeapi.co/api/v2/pokemon/pikachu").retrieve().bodyToMono(String.class).block()).thenReturn(pikachuJSON);
        // // Mockito.when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        // // Mockito.when(responseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(pikachuJSON));

        // // Pokemon Species Request
        // String speciesJSON = getStringFromFile("src\\test\\resources\\pikachu_species.json");

        // Mockito.when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
        // Mockito.when(requestHeadersUriSpecMock.uri("https://pokeapi.co/api/v2/pokemon-species/pikachu")).thenReturn(requestHeadersSpecMock);
        // Mockito.when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        // Mockito.when(responseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(speciesJSON));

        // // Pokemon Evolution Request
        // String evolutionJSON = getStringFromFile("src\\test\\resources\\pikachu_evolution.json");

        // Mockito.when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
        // Mockito.when(requestHeadersUriSpecMock.uri("https://pokeapi.co/api/v2/evolution-chain/10")).thenReturn(requestHeadersSpecMock);
        // Mockito.when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        // Mockito.when(responseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(evolutionJSON));

        // // Pokemon Evolution Request
        // String locationJSON = getStringFromFile("src\\test\\resources\\pikachu_location.json");

        // Mockito.when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
        // Mockito.when(requestHeadersUriSpecMock.uri("https://pokeapi.co/api/v2/pokemon/25/encounters")).thenReturn(requestHeadersSpecMock);
        // Mockito.when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        // Mockito.when(responseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(locationJSON));

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
