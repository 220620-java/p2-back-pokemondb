package com.revature.pokemondb.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.repositories.PokemonRepository;
import com.revature.pokemondb.utils.StringUtils;

import reactor.core.publisher.Mono;

// GRAB FROM pokemon-species for description, generation, and evolution chain
// https://pokeapi.co/docs/v2#pokemon-species
/**
 * This class retrieves pokemon from the PokeAPI
 * We want to grab the following pokemon fields:
 * Id
 * - [id]
 * Name
 * - [name]
 * Height
 * - [height]
 * Weight
 * - [weight]
 * Generation (from pokemon-species/)
 * Location (from /encounters)
 * Type
 * - [types][0][type][name]
 * Weaknesses (from type, damage_relations)
 * Category (from pokemon-species/, genera[7])
 * Gender (from gender)
 * Stats
 * - Base Stat: [stats][#][base_stat]
 * - Name: [stats][#][base_stat]
 */
@Service
public class PokemonServiceImpl implements PokemonService{
    private PokemonRepository pokeRepo;
    private ObjectMapper objMapper;
    private WebClient client;

    public PokemonServiceImpl(ObjectMapper objMapper, PokemonRepository pRepo) {
        this.client = WebClient.create();
        this.objMapper = objMapper;
        this.pokeRepo = pRepo;
    }

    /**
     * Send Get Request to External API
     * https://reflectoring.io/spring-webclient/
     * @param url
     * @return
     */
    public String getJSON(String url) {
        Mono<String> response  = client.get().uri(url).retrieve().bodyToMono(String.class);
        return response.block();
    }

    /**
     * Get a default pokemon: pikachu
     * 
     * @return
     */
    public String getPokemon() {
        String url = "https://pokeapi.co/api/v2/pokemon/pikachu";
        return getJSON(url);
    }

    /**
     * External API CALL - Grab Pokemon by Id
     * 
     * @param pokemonName
     * @return
     */
    public String getPokemonJSON(int pokemonId) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + pokemonId;
        return getJSON(url);
    }

    /**
     * External API CALL - Grab Pokemon by Name
     * 
     * @param pokemonName
     * @return
     */
    public String getPokemonJSON(String pokemonName) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + StringUtils.convertToURIFormat(pokemonName);
        return getJSON(url);
    }

    /**
     * External API CALL - Grab Pokemon Species by Name
     * 
     * @param pokemonName
     * @return
     */
    public String getPokemonSpeciesJSON(String pokemonName) {
        String url = "https://pokeapi.co/api/v2/pokemon-species/" + StringUtils.convertToURIFormat(pokemonName);
        return getJSON(url);
    }

    /**
     * External API CALL - Get Evolution Chain
     * 
     * @param evolutionID
     * @return
     */
    public String getEvolutionChainJSON(int evolutionID) {
        String url = "https://pokeapi.co/api/v2/evolution-chain/" + evolutionID;
        return getJSON(url);
    }


    public Pokemon getReferencePokemon(int id) {
        Optional<Pokemon> pokemon = pokeRepo.findById(id);
        if (pokemon.isPresent()) {return pokemon.get();}
        return null;
    }

    public Pokemon getReferencePokemon(String pokemonName) {
        Optional<Pokemon> pokemon = pokeRepo.findByName(pokemonName);
        if (pokemon.isPresent()) {return pokemon.get();}
        return null;
    }

    public Pokemon createPokemon (int pokemonId) {
        Pokemon pokemon = createPokemonObject(getPokemonJSON(pokemonId));
        pokeRepo.save(pokemon);
        return pokemon;
    }

    public Pokemon createPokemon (String pokemonName) {
        Pokemon pokemon = createPokemonObject(getPokemonJSON(pokemonName));
        pokeRepo.save(pokemon);
        return pokemon;
    }

    public Pokemon createPokemonObject (String pokemonJSON) {
        Pokemon pokemon;
        try {

            JsonNode pokemonRoot = objMapper.readTree(pokemonJSON);
            
            // Id
            int id = pokemonRoot.get("id").asInt();

            // Name
            String name = pokemonRoot.get("name").asText();

            // Height
            int height = pokemonRoot.get("height").asInt();

            // Weight
            int weight = pokemonRoot.get("weight").asInt();

            // Types
            String[] types;
            JsonNode typesNode = pokemonRoot.get("types").get(0);
            int typesSize = typesNode.size();
            types = new String[typesSize];
            for (int i = 0; i < typesSize; i++) {
                types[i] = typesNode.get("type").get("name").asText();
            }

            // Base Stats
            Map<String, Integer> baseStats = new HashMap<>();
            for (JsonNode jsonNode : pokemonRoot.get("stats")) {
                String baseStatName = jsonNode.get("stat").get("name").asText();
                int baseStatNumber = jsonNode.get("base_stat").asInt();
                baseStats.put(baseStatName, baseStatNumber);
            }

            // Image URL
            String imageURL = pokemonRoot.get("sprites").get("other").get("official-artwork").get("front_default").asText();

            // Species JSON
            String speciesJSON = getPokemonSpeciesJSON(name);
            JsonNode speciesRoot = objMapper.readTree(speciesJSON);

            // Generation
            String generation = speciesRoot.get("generation").get("name").asText();
            String number = generation.split("-")[1];
            int genNum = Integer.valueOf(StringUtils.getNumberFromRomanNumeral(number));

            // Category
            String category = speciesRoot.get("genera").get(7).get("genus").asText();
            
            // Description
            String description = "No Description";
            int flavorTextSize = speciesRoot.get("flavor_text_entries").size();
            for (int i = 0; i < flavorTextSize; i++) {
                JsonNode currentNode = speciesRoot.get("flavor_text_entries").get(i);
                String languageName = currentNode.get("language").get("name").asText();

                // String uses equals instead of == so it won't be false
                // Grab the first English flavor text description
                if (languageName.equals("en")) {
                    description = speciesRoot.get("flavor_text_entries").get(i).get("flavor_text").asText();
                    description = description.replace("\f", " ");
                    description = description.replace("\n", " ");
                    break;
                }
            }

            // Evolution JSON
            String evolutionURL = objMapper.readTree(speciesJSON).get("evolution_chain").get("url").asText();
            String evolutionJSON = getJSON(evolutionURL);
            JsonNode evolutionRoot = objMapper.readTree(evolutionJSON);

            // Evolution Chain
            List<String[]> evolutionChain = new ArrayList<>();
            JsonNode evolutionChainNode = evolutionRoot.get("chain");
            do {
                String speciesName = evolutionChainNode.get("species").get("name").asText();
                speciesName = StringUtils.convertFromURIFormat(speciesName);
                String speciesURL = evolutionChainNode.get("species").get("url").asText();
                evolutionChain.add(new String[]{speciesName, speciesURL});
                evolutionChainNode = evolutionChainNode.get("evolves_to").get(0);
            } while (evolutionChainNode != null);

            // Location JSON
            String locationsURL = objMapper.readTree(pokemonJSON).get("location_area_encounters").asText();
            String locationJSON = getJSON(locationsURL);
            JsonNode locationRoot = objMapper.readTree(locationJSON);

            // Locations/Versions
            List<Map<String,String>> locationVersions = new ArrayList<>();

            for (JsonNode locationNode : locationRoot) {
                // Location
                String locationName = locationNode.get("location_area").get("name").asText();
                locationName = StringUtils.convertFromURIFormat(locationName);

                String locationURL = locationNode.get("location_area").get("url").asText();
                
                // Versions
                JsonNode versionNodes = locationNode.get("version_details");
                for (JsonNode versionNode : versionNodes) {
                    Map<String, String> versionMap = new HashMap<>();

                    // Location Name
                    versionMap.put ("locationName", locationName);
                    
                    // URL
                    versionMap.put ("locationURL", locationURL);

                    // Encounter Method
                    JsonNode encounterNode = versionNode.get("encounter_details");
                    Set<String> encounterSet = new HashSet<>();
                    
                    // StringJoiner joiner = new StringJoiner(",");
                    for (JsonNode encounter : encounterNode) {
                        encounterSet.add(encounter.get("method").get("name").asText());
                    }
                    versionMap.put ("methods", encounterSet.toString());

                    // Max Chance
                    String maxChance = versionNode.get("max_chance").asText() + "%";
                    versionMap.put("maxChance", maxChance);

                    // Version
                    String version = versionNode.get("version").get("name").asText();
                    versionMap.put("versionName", version);

                    locationVersions.add(versionMap);
                }
            }

            pokemon = new Pokemon (id, name,
                height,
                weight,
                types,
                baseStats,
                imageURL,
                genNum,
                category,
                description,
                evolutionChain,
                locationVersions
            );
            return pokemon;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
