package com.revature.pokemondb.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.pokemondb.PokemondbApplication;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.models.dtos.UserDTO;

@SpringBootTest(classes=PokemondbApplication.class)
public class PokemonTest {
    
    @Test
    void createPokemon() {
        Pokemon pokemon = new Pokemon();
        assertNotNull(pokemon);
    }

    @Test
    void createPokemonId() {
        Pokemon pokemon = new Pokemon(1);
        assertNotNull(pokemon);
    }

    @Test
    void pokemonId() {
        Pokemon pokemon = new Pokemon();
        int expectedId = 2;
        pokemon.setId(expectedId);
        assertEquals(pokemon.getId(), expectedId);
    }

    @Test
    void pokemonName() {
        Pokemon pokemon = new Pokemon();
        String expectedName = "pikachu";
        pokemon.setName(expectedName);
        assertEquals(pokemon.getName(), expectedName);
    }

    @Test
    void pokemonHeight() {
        Pokemon pokemon = new Pokemon();
        int expectedHeight = 3;
        pokemon.setHeight(expectedHeight);
        assertEquals(pokemon.getHeight(), expectedHeight);
    }

    @Test
    void pokemonWeight() {
        Pokemon pokemon = new Pokemon();
        int expectedWeight = 3;
        pokemon.setWeight(expectedWeight);
        assertEquals(pokemon.getWeight(), expectedWeight);
    }

    @Test
    void pokemonTypes() {
        Pokemon pokemon = new Pokemon();
        String[] expectedTypes = new String[] {"electric", "ground"};
        pokemon.setTypes(expectedTypes);
        assertEquals(pokemon.getTypes(), expectedTypes);
    }

    @Test
    void pokemonBaseStats() {
        Pokemon pokemon = new Pokemon();
        Map<String, Integer> expectedBaseStats = new HashMap<String, Integer>();
        expectedBaseStats.put("hp", 45);
        expectedBaseStats.put("attack", 49);
        expectedBaseStats.put("defense", 49);
        expectedBaseStats.put("speed", 45);
        expectedBaseStats.put("special-attack", 65);
        expectedBaseStats.put("special-defense", 65);
        pokemon.setBaseStats(expectedBaseStats);
        assertEquals(pokemon.getBaseStats(), expectedBaseStats);
    }

    @Test
    void pokemonImageUrl() {
        Pokemon pokemon = new Pokemon();
        String expectedUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png";
        pokemon.setImageUrl(expectedUrl);
        assertEquals (pokemon.getImageUrl(), expectedUrl);
    }

    @Test
    void pokemonGeneration() {
        Pokemon pokemon = new Pokemon();
        int expectedGen = 1;
        pokemon.setGeneration(expectedGen);
        assertEquals(pokemon.getGeneration(), expectedGen);
    }

    @Test
    void pokemonCategory() {
        Pokemon pokemon = new Pokemon();
        String expectedCategory = "Seed Pokémon";
        pokemon.setCategory(expectedCategory);
        assertEquals(pokemon.getCategory(), expectedCategory);
    }

    @Test
    void pokemonDescription() {
        Pokemon pokemon = new Pokemon();
        String expectedDescription = "A strange seed was planted on its back at birth. The plant sprouts and grows with this POKéMON.";
        pokemon.setDescription(expectedDescription);
        assertEquals(pokemon.getDescription(), expectedDescription);
    }

    @Test
    void pokemonEvolution() {
        Pokemon pokemon = new Pokemon();
        List<String[]> expectedEvolution = new ArrayList<>();
        expectedEvolution.add(new String[] {"Pokemon", "URL"});
        pokemon.setEvolutionChain(expectedEvolution);
        assertEquals(pokemon.getEvolutionChain(), expectedEvolution);
    }

    @Test
    void pokemonLocation() {
        Pokemon pokemon = new Pokemon();
        List<Map<String, String>> expectedLocation = new ArrayList<>();
        pokemon.setLocationVersions(expectedLocation);
        assertEquals(pokemon.getLocationVersions(), expectedLocation);
    }

    @Test
    void pokemonBaseXp() {
        Pokemon pokemon = new Pokemon();
        int expectedExperience = 20;
        pokemon.setBaseExperience(expectedExperience);
        assertEquals(pokemon.getBaseExperience(), expectedExperience);
    }

    @Test
    void pokemonAbilities() {
        Pokemon pokemon = new Pokemon();
        List<Ability> expectedAbilities = new ArrayList<>();
        Ability ability = new Ability("name", "url", 2, true);
        expectedAbilities.add(ability);
        pokemon.setAbilities(expectedAbilities);
        assertEquals(pokemon.getAbilities(), expectedAbilities);
    }

    @Test
    void pokemonMoves() {
        Pokemon pokemon = new Pokemon();
        PokemonMoves expectedMoves = new PokemonMoves(new HashSet<Move>(), new HashSet<Move>(), new HashSet<Move>(), new HashSet<Move>(), new HashSet<Move>());
        Move move = new Move("name", "url", "level-up", 2);
        Set<Move> levelMoves = new HashSet<Move>();
        levelMoves.add(move);
        expectedMoves.setLevelMoves(levelMoves);
        pokemon.setMoves(expectedMoves);
        assertEquals (pokemon.getMoves(), expectedMoves);
    }
}
