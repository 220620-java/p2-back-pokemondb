package com.revature.pokemondb.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    @MockBean
    private AuthController authController;

    @MockBean
    private UserController userController;

    @MockBean
    private UserService userService;

    @MockBean
    private PokemonRepository pokemonRepo;

    @Autowired
    @Spy
    private PokemonService pokemonService;

    @Test
    void createPokemonByName() {
        PokemonDTO dto = new PokemonDTO();
        Mockito.when(pokemonRepo.save(dto)).thenReturn(dto);
        Pokemon pokemon = pokemonService.createPokemon("pikachu");
        assertNotNull(pokemon);
    }

    @Test
    void createPokemonById () {
        PokemonDTO dto = new PokemonDTO();
        Mockito.when(pokemonRepo.save(dto)).thenReturn(dto);
        Pokemon pokemon = pokemonService.createPokemon(1);
        assertNotNull(pokemon);
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
}
