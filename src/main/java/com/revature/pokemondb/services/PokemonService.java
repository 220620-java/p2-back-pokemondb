package com.revature.pokemondb.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.utils.StringUtils;

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
public class PokemonService {
    private ObjectMapper objMapper = new ObjectMapper();

    private final RestTemplate restTemplate;

    public PokemonService(RestTemplateBuilder restBuilder) {
        this.restTemplate = restBuilder.build();
    }

    /**
     * Send Get Request to External API
     * 
     * @param url
     * @return
     */
    public String getJSON(String url) {
        return this.restTemplate.getForObject(url, String.class);
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

    /**
     * Convert the json from PokeAPI into a usable Map
     * 
     * @param jsonObj
     * @return
     */
    public Map<?, ?> convertJsonIntoMap(String jsonObj) {
        Map<?, ?> pokemonMap = new HashMap<>();
        try {
            pokemonMap = objMapper.readValue(jsonObj, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return pokemonMap;
    }

    public Pokemon createPokemon (int pokemonId) {
        return createPokemonObject(getPokemonJSON(pokemonId));
    }

    public Pokemon createPokemon (String pokemonName) {
        return createPokemonObject(getPokemonJSON(pokemonName));
    }

    public Pokemon createPokemonObject (String pokemonJSON) {
        Pokemon pokemon;
        try {

            JsonNode pokemonRoot = objMapper.readTree(pokemonJSON);
            
            // Id
            int id = pokemonRoot.get("id").asInt();

            // Name
            String name = StringUtils.convertToTitleCase(pokemonRoot.get("name").asText());

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
            generation = String.valueOf(StringUtils.getNumberFromRomanNumeral(number));

            // Category
            String category = speciesRoot.get("genera").get(7).get("genus").asText();
            
            // Description
            String description = speciesRoot.get("flavor_text_entries").get(0).get("flavor_text").asText();
            description += " " + speciesRoot.get("flavor_text_entries").get(1).get("flavor_text").asText();
            description = description.replace("\f", " ");
            description = description.replace("\n", " ");

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

            pokemon = new Pokemon (
                id,
                name,
                height,
                weight,
                types,
                baseStats,
                imageURL,
                generation,
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

    /**
     * https://pokeapi.co/docs/v2#pokemon
     * Name
     * - [name]
     * Height
     * - [height]
     * Weight
     * - [weight]
     * Type
     * - [types][0][type][name]
     * Stats
     * - Base Stat: [stats][#][base_stat]
     * - Name: [stats][#][stat][name]
     * 
     * @param pokemonMap
     */
    public void printPokemonJSON(String json) {
        try {
            JsonNode root;
            root = objMapper.readTree(json);

            // Id
            Integer id = root.get("id").asInt();
            System.out.println("Id: " + id);
    
            // Name
            String name = root.get("name").asText();
            // Title Case 
            name = StringUtils.convertToTitleCase(name);
            System.out.println("Name: " + name);
    
            // Height in decimetres
            Integer height = root.get("height").asInt();
            Float heightInInches = height * 3.937f;
            Integer feet = (int) (heightInInches / 12);
            String inches = StringUtils.formatDecimalPlaces(heightInInches % 12, 2);
            System.out.println("Height: " + height + " decimetres | " + feet + "\'" + inches + "\"");
    
            // Weight in hectograms
            Integer weight = root.get("weight").asInt();
            Float pounds = weight / 4.536f;
            System.out.println("Weight: " + weight + " hectograms | " + pounds + "lb");

            // Type
            for (JsonNode jsonNode : root.get("types")) {
                String type = jsonNode.get("type").get("name").asText();
                System.out.println("Type Name: " + type);
            }

            // Stats
            System.out.println("Base Stats");
            for (JsonNode jsonNode : root.get("stats")) {
                String baseStatNumber = jsonNode.get("base_stat").asText();
                String baseStatName = jsonNode.get("stat").get("name").asText();
                System.out.println("\t" + baseStatName + ": " + baseStatNumber);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Description
     * Category
     * Generation
     * Evolution Chain
     * @param json
     */
    public void printPokemonSpeciesJSON(String json) {
        try {
            JsonNode root;
            root = objMapper.readTree(json);

            // Generation
            String generation = root.get("generation").get("name").asText();
            System.out.println("Generation: " + StringUtils.convertFromURIFormat(generation));

            // Category
            String category = root.get("genera").get(7).get("genus").asText();
            System.out.println("Category: " + category);

            // Description
            String description = root.get("flavor_text_entries").get(0).get("flavor_text").asText();
            description += " " + root.get("flavor_text_entries").get(1).get("flavor_text").asText();
            description = description.replace("\f", " ");
            description = description.replace("\n", " ");
            System.out.println("Description: " + description);

            // Evolution Chain
            String evolutionChain = root.get("evolution_chain").get("url").asText();
            System.out.println("Evolution Chain URL: " + evolutionChain);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void printEvolutionChainJSON(String json) {
        try {
            JsonNode root = objMapper.readTree(json);

            // Evolution Chain
            List<String[]> evolutionChain = new ArrayList<>();
            JsonNode currentNode = root.get("chain");
            do {
                String speciesName = currentNode.get("species").get("name").asText();
                speciesName = StringUtils.convertFromURIFormat(speciesName);
                String speciesURL = currentNode.get("species").get("url").asText();
                evolutionChain.add(new String[]{speciesName, speciesURL});
                currentNode = currentNode.get("evolves_to").get(0);
            } while (currentNode != null);
            System.out.println("Evolution Chain: ");
            for (String[] species : evolutionChain) {
                System.out.println("\t" + species[0] + ": " + species[1]);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void printLocationJSON (String json) {
        try {
            JsonNode root = objMapper.readTree(json);
            System.out.println("Locations: ");
            for (JsonNode locationNode : root) {
                String name = locationNode.get("location_area").get("name").asText();
                name = StringUtils.convertFromURIFormat(name);
                String url = locationNode.get("location_area").get("url").asText();
                String locationString = "\t" + name;
                System.out.println(locationString + " URL: " + url);
                JsonNode versionNodes = locationNode.get("version_details");
                for (JsonNode versionNode : versionNodes) {
                    String maxChance = versionNode.get("max_chance").asText() + "%";
                    String version = versionNode.get("version").get("name").asText();
                    System.out.println("\t\t" + "Version: " + version + " " + maxChance);
                }
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void printPokemonInformation (String pokemonName) {
        System.out.println("-------------------");
        String pokemonJSON = getPokemonJSON(pokemonName);
        printPokemonJSON(pokemonJSON);
        String speciesJSON = getPokemonSpeciesJSON(pokemonName);
        printPokemonSpeciesJSON(speciesJSON);
        try {
            String locationsURL = objMapper.readTree(pokemonJSON).get("location_area_encounters").asText();
            printLocationJSON(getJSON(locationsURL));
            String evolutionURL = objMapper.readTree(speciesJSON).get("evolution_chain").get("url").asText();
            printEvolutionChainJSON(getJSON(evolutionURL));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("-------------------");
    }
}
