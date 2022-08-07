package com.revature.pokemondb.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.Ability;
import com.revature.pokemondb.models.Move;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.models.PokemonMoves;
import com.revature.pokemondb.models.dtos.PokemonDTO;
import com.revature.pokemondb.repositories.PokemonRepository;
import com.revature.pokemondb.utils.StringUtils;


@Service("PokemonService")
public class PokemonServiceImpl implements PokemonService{
    private PokemonRepository pokeRepo;
    private WebClientService webClient;

    @Autowired
    private ObjectMapper objMapper;


    public PokemonServiceImpl(PokemonRepository pRepo, WebClientService webClient) {
        this.pokeRepo = pRepo;
        this.webClient = webClient;
    }

    /**
     * External API CALL - Grab Pokemon by Id
     * 
     * @param pokemonName
     * @return
     */
    public String getPokemonJSON(int pokemonId) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + pokemonId;
        return webClient.getRequestJSON(url);
    }

    /**
     * External API CALL - Grab Pokemon by Name
     * 
     * @param pokemonName
     * @return
     */
    public String getPokemonJSON(String pokemonName) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + StringUtils.convertToURIFormat(pokemonName);
        return  webClient.getRequestJSON(url);
    }

    /**
     * External API CALL - Grab Pokemon Species by Id
     * 
     * @param pokemonName
     * @return
     */
    public String getPokemonSpeciesJSON(int id) {
        String url = "https://pokeapi.co/api/v2/pokemon-species/" + id;
        return  webClient.getRequestJSON(url);
    }

    /**
     * 
     */
    public PokemonDTO getReferencePokemon(int id) {
        Optional<PokemonDTO> pokemon = pokeRepo.findById(id);
        if (pokemon.isPresent()) {return pokemon.get();}
        return null;
    }

    /**
     * 
     */
    public PokemonDTO getReferencePokemon(String pokemonName) {
        Optional<PokemonDTO> pokemon = pokeRepo.findByName(pokemonName);
        if (pokemon.isPresent()) {return pokemon.get();}
        return null;
    }

    /**
     * 
     */
    public Pokemon createPokemon (int pokemonId) {
        Pokemon pokemon = createPokemonObject(getPokemonJSON(pokemonId));
        PokemonDTO dto = new PokemonDTO(pokemon);
        pokeRepo.save(dto);
        return pokemon;
    }

    /**
     * 
     */
    public Pokemon createPokemon (String pokemonName) {
        Pokemon pokemon = createPokemonObject(getPokemonJSON(pokemonName));
        PokemonDTO dto = new PokemonDTO(pokemon);
        pokeRepo.save(dto);
        return pokemon;
    }

    /**
     * 
     */
    public List<PokemonDTO> getAllPokemonById (List<Integer> ids) {
        List<PokemonDTO> pokemonList = new ArrayList<>();
        // Retrieve pokemon one by one
        for (Integer id : ids) {
            Optional<PokemonDTO> pokemon = pokeRepo.findById(id);
            if (pokemon.isPresent())
                pokemonList.add(pokemon.get());
        }
        return pokemonList;
    }

    /**
     * Create a pokemon object using four different API calls from PokeAPI.
     */
    public Pokemon createPokemonObject (String pokemonJSON) {
        Pokemon pokemon = null;
        try {
            // ---------------------------Pokemon JSON--------------------------------------
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
            String[] types = getTypes(pokemonRoot.get("types").get(0));

            // Base Stats
            Map<String, Integer> baseStats = getBaseStats(pokemonRoot.get("stats"));

            // Image URL
            String imageURL = pokemonRoot.get("sprites").get("other").get("official-artwork").get("front_default").asText();

            // Base Experience
            int baseExperience = pokemonRoot.get("base_experience").asInt();

            // Abilities
            List<Ability> abilities = getAbilities(pokemonRoot.get("abilities"));
            
            // Moves
            PokemonMoves moves = getPokemonMoves (pokemonRoot.get("moves"));

            // ----------------------------Species JSON-----------------------------------------
            String speciesJSON = getPokemonSpeciesJSON(id);
            JsonNode speciesRoot = objMapper.readTree(speciesJSON);

            // Generation
            String generation = speciesRoot.get("generation").get("name").asText();
            String number = generation.split("-")[1];
            int genNum = StringUtils.getNumberFromRomanNumeral(number);

            // Category
            String category = speciesRoot.get("genera").get(7).get("genus").asText();
            
            // Description
            String description = getDescription(speciesRoot.get("flavor_text_entries"));

            // ---------------------------------Evolution JSON-------------------------------
            String evolutionURL = objMapper.readTree(speciesJSON).get("evolution_chain").get("url").asText();
            String evolutionJSON =  webClient.getRequestJSON(evolutionURL);
            JsonNode evolutionRoot = objMapper.readTree(evolutionJSON);

            // Evolution Chain
            List<String[]> evolutionChain = getEvolutionChain(evolutionRoot.get("chain"));

            // ----------------------------Location JSON-----------------------------
            String locationsURL = objMapper.readTree(pokemonJSON).get("location_area_encounters").asText();
            String locationJSON =  webClient.getRequestJSON(locationsURL);
            JsonNode locationRoot = objMapper.readTree(locationJSON);

            // Locations/Versions
            List<Map<String,String>> locationVersions = getLocationVersions(locationRoot);

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
                locationVersions,
                baseExperience,
                abilities,
                moves
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return pokemon;
    }

    /**
     * 
     * @param node
     * @return
     */
    private String[] getTypes (JsonNode node) {
        int size = node.size();
        String[] types = new String[size];
        for (int i = 0; i < size; i++) {
            types[i] = node.get("type").get("name").asText();
        }
        return types;
    }

    /**
     * 
     * @param node
     * @return
     */
    private Map<String, Integer> getBaseStats (JsonNode node) {
        Map<String, Integer> baseStats = new HashMap<>();
        for (JsonNode jsonNode : node) {
            String baseStatName = jsonNode.get("stat").get("name").asText();
            int baseStatNumber = jsonNode.get("base_stat").asInt();
            baseStats.put(baseStatName, baseStatNumber);
        }
        return baseStats;
    }

    /**
     * 
     * @param node
     * @return
     */
    private List<Ability> getAbilities (JsonNode node) {
        List<Ability> abilities = new ArrayList<>();
        for (JsonNode jsonNode : node) {
            Ability newAbility = new Ability(
                jsonNode.get("ability").get("name").asText(),
                jsonNode.get("ability").get("url").asText(),
                jsonNode.get("slot").asInt(),
                jsonNode.get("is_hidden").asBoolean()
            );
            abilities.add(newAbility);
        }
        return abilities;
    }

    /**
     * 
     * @param node
     * @return
     */
    private PokemonMoves getPokemonMoves (JsonNode node) {
        Set<Move> levelMoves = new HashSet<>();
        Set<Move> eggMoves = new HashSet<>();
        Set<Move> tutorMoves = new HashSet<>();
        Set<Move> machineMoves = new HashSet<>();
        Set<Move> otherMoves = new HashSet<>();
        for (JsonNode jsonNode : node) {
            Move move = new Move();

            move.setName(jsonNode.get("move").get("name").asText());
            move.setURL(jsonNode.get("move").get("url").asText());

            for (JsonNode versionGroupDetailsNode : jsonNode.get("version_group_details")) {
                move.setTypeOfMove(versionGroupDetailsNode.get("move_learn_method").get("name").asText());
                move.setLevelLearnedAt(versionGroupDetailsNode.get("level_learned_at").asInt());

                switch (move.getTypeOfMove()) {
                    case ("level-up"):
                        levelMoves.add(move);
                        break;
                    case ("egg"):
                        eggMoves.add(move);
                        break;
                    case ("tutor"):
                        tutorMoves.add(move);
                        break;
                    case ("machine"):
                        machineMoves.add(move);
                        break;
                    default:
                        otherMoves.add(move);
                        break;
                }
            }
        }
        return new PokemonMoves(levelMoves, eggMoves, tutorMoves, machineMoves, otherMoves);
    }

    /**
     * 
     * @param flavorTextEntriesNode
     * @return
     */
    private String getDescription (JsonNode flavorTextEntriesNode) {
        String description = "No Description";
        int flavorTextSize = flavorTextEntriesNode.size();
        for (int i = 0; i < flavorTextSize; i++) {
            JsonNode currentNode = flavorTextEntriesNode.get(i);
            String languageName = currentNode.get("language").get("name").asText();

            // String uses equals instead of == so it won't be false
            // Grab the first English flavor text description
            if (languageName.equals("en")) {
                description = flavorTextEntriesNode.get(i).get("flavor_text").asText();
                description = description.replace("\f", " ");
                description = description.replace("\n", " ");
                break;
            }
        }
        return description;
    }

    /**
     * 
     * @param evolutionChainNode
     * @return
     */
    private List<String[]> getEvolutionChain (JsonNode evolutionChainNode) {
        List<String[]> evolutionChain = new ArrayList<>();
        do {
            String speciesName = evolutionChainNode.get("species").get("name").asText();
            speciesName = StringUtils.convertFromURIFormat(speciesName);
            String speciesURL = evolutionChainNode.get("species").get("url").asText();
            evolutionChain.add(new String[]{speciesName, speciesURL});
            evolutionChainNode = evolutionChainNode.get("evolves_to").get(0);
        } while (evolutionChainNode != null);

        return evolutionChain;
    }

    /**
     * 
     * @param locationRoot
     * @return
     */
    private List<Map<String, String>> getLocationVersions (JsonNode locationRoot) {
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
        return locationVersions;
    }
}
