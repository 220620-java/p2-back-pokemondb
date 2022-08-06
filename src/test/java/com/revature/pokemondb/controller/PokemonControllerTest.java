package com.revature.pokemondb.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.services.PokemonService;

@WebMvcTest(controllers=PokemonController.class)
class PokemonControllerTest {
    @MockBean
    private PokemonService pokemonService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    /** 
     * @throws Exception
     */
    @Test
    void testOptionsRequest() throws Exception {
        mockMvc.perform(options("/pokemon/"))
            .andExpect(status().isOk());
    }

    @Test
    void getHelloWorld() throws Exception {
        mockMvc.perform(get("/pokemon/"))
        .andExpect(status().isOk());
    }

    @Test
    void getAllPokemonById() throws Exception {
        List<Integer> mockIdList = Arrays.asList(1,2,3);
        List<Pokemon> mockPokemonList = new ArrayList<>();
        Pokemon mockPokemon = new Pokemon();
        Pokemon mockPokemon2 = new Pokemon();
        Pokemon mockPokemon3 = new Pokemon();
        mockPokemonList.add(mockPokemon);
        mockPokemonList.add(mockPokemon2);
        mockPokemonList.add(mockPokemon3);
        Mockito.when(pokemonService.getAllPokemonById(mockIdList)).thenReturn(mockPokemonList);
        mockMvc.perform(post("/pokemon/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockIdList)))
            .andExpect(status().isOk());
    }

    @Test
    void getAllPokemonByIdEmptyList() throws Exception {
        List<Integer> mockIdList = Arrays.asList(1,2,3);
        List<Pokemon> mockPokemonList = new ArrayList<>();
        Mockito.when(pokemonService.getAllPokemonById(mockIdList)).thenReturn(mockPokemonList);
        mockMvc.perform(post("/pokemon/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockIdList)))
            .andExpect(status().isOk());
    }

    @Test
    void getAllPokemonByIdNull() throws Exception {
        List<Integer> mockIdList = Arrays.asList(1,2,3);
        List<Pokemon> mockPokemonList = new ArrayList<>();
        Pokemon mockPokemon = new Pokemon();
        Pokemon mockPokemon2 = new Pokemon();
        Pokemon mockPokemon3 = new Pokemon();
        mockPokemonList.add(mockPokemon);
        mockPokemonList.add(mockPokemon2);
        mockPokemonList.add(mockPokemon3);
        Mockito.when(pokemonService.getAllPokemonById(mockIdList)).thenReturn(null);
        mockMvc.perform(post("/pokemon/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockIdList)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getPokemonByID() throws Exception {
        Pokemon mockPokemon = new Pokemon();
        Mockito.when(pokemonService.createPokemon(1)).thenReturn(mockPokemon);
        mockMvc.perform(get("/pokemon/1"))
            .andExpect(status().isOk());
    }

    @Test
    void getPokemonByName() throws Exception {
        Pokemon mockPokemon = new Pokemon();
        Mockito.when(pokemonService.createPokemon("pikachu")).thenReturn(mockPokemon);
        mockMvc.perform(get("/pokemon/pikachu"))
            .andExpect(status().isOk());
    }

    @Test
    void getPokemonByBadName() throws Exception {
        Mockito.when(pokemonService.createPokemon("fakePokemon")).thenReturn(null);
        mockMvc.perform(get("/pokemon/fakePokemon"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getPokemonByIDShort() throws Exception {
        Pokemon mockPokemon = new Pokemon();
        Mockito.when(pokemonService.getReferencePokemon(1)).thenReturn(mockPokemon);
        mockMvc.perform(get("/pokemon/poke/1"))
            .andExpect(status().isOk());
    }

    @Test
    void getPokemonByNameShort() throws Exception {
        Pokemon mockPokemon = new Pokemon();
        Mockito.when(pokemonService.getReferencePokemon("pikachu")).thenReturn(mockPokemon);
        mockMvc.perform(get("/pokemon/poke/pikachu"))
            .andExpect(status().isOk());
    }

    @Test
    void getPokemonByBadNameShort() throws Exception {
        Mockito.when(pokemonService.getReferencePokemon("fakePokemon")).thenReturn(null);
        mockMvc.perform(get("/pokemon/poke/fakePokemon"))
            .andExpect(status().isBadRequest());
    }
}
