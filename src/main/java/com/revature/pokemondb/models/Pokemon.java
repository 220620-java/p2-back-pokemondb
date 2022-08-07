package com.revature.pokemondb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.pokemondb.models.dtos.PokemonDTO;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.Map.Entry;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name= "pokemon", schema="pokemon_db")
public class Pokemon {
	
	@Id
	@Column
    private int id;

	@Column(name="name")
    private String name;

	@Transient 
    private int height;

	@Transient
    private int weight;

	@Transient
	private String[] types;

	@Transient
	private Map<String, Integer> baseStats;

	@Column(name="sprite")
	private String imageUrl;

	@Column(name="gen")
    private int generation;

	@Transient
    private String category;

	@Transient
    private String description;

	@Transient
    private List<String[]> evolutionChain;

    @Transient
    private List<Map<String, String>> locationVersions;

    @Transient
    private int baseExperience;

    // Pokemon have 1 or 2 abilities and then a 3rd hidden ability sometimes.
    @Transient
    private List<Ability> abilities;

    @Transient
    private PokemonMoves moves;

    public Pokemon () {
        this.id = 0;
        this.name = "";
        this.height = 0;
        this.weight = 0;
        this.types = new String[0];
        this.baseStats = new HashMap<>();
        this.imageUrl = "";
        this.generation = 1;
        this.category = "";
        this.description = "";
        this.evolutionChain = new ArrayList<>();
        this.locationVersions = new ArrayList<>();
        this.baseExperience = 0;
        this.abilities = new ArrayList<>();
        this.moves = new PokemonMoves();
    }
    
    public Pokemon (int id) {
        this.id = id;
        this.name = "";
        this.height = 0;
        this.weight = 0;
        this.types = null;
        this.baseStats = new HashMap<>();
        this.imageUrl = "";
        this.generation = 1;
        this.category = "";
        this.description = "";
        this.evolutionChain = new ArrayList<>();
        this.locationVersions = new ArrayList<>();
        this.baseExperience = 0;
        this.abilities = new ArrayList<>();
        this.moves = new PokemonMoves();
    }

   @Autowired
    public Pokemon (
        int id,
        String name,
        int height,
        int weight,
        String[] types,
        Map<String, Integer> baseStats,
        String imageUrl,
        int generation,
        String category,
        String description,
        List<String[]> evolutionChain,
        List<Map<String, String>> locationVersions,
        int baseExperience,
        List<Ability> abilities,
        PokemonMoves moves
    ) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.types = types;
        this.baseStats = baseStats;
        this.imageUrl = imageUrl;
        this.generation = generation;
        this.category = category;
        this.description = description;
        this.evolutionChain = evolutionChain;
        this.locationVersions = locationVersions;
        this.baseExperience = baseExperience;
        this.abilities = abilities;
        this.moves = moves;
    }

    public Pokemon(PokemonDTO pokemon) {
        this.id = pokemon.getId();
        this.name = pokemon.getName();
        this.generation = pokemon.getGeneration();
        this.imageUrl = pokemon.getImageUrl();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setHeightFromInches(float heightInInches) {
        this.height = (int) (heightInInches * 0.254f);
    }

    public float getHeightInInches() {
        return getHeight() * 3.937f;
    }

    public float getHeightInFeet() {
        return getHeight() * 0.328084f;
    }

    public String getHeightInFeetInches() {
        float heightInInches = getHeight() * 3.937f;
        int feet = (int) (heightInInches / 12);
        String inches = String.valueOf(Math.round(heightInInches % 12));
        return feet + "\'" + inches + "\"";
    }

    public int getWeight() {
        return weight;
    }

    public float getWeightInPounds() {
        return weight / 4.536f;
    }

    public String getWeightInPoundsString() {
        float pounds = weight / 4.536f;
        return pounds + "lb";
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setWeightFromPounds(float weight) {
        this.weight = (int) (weight * 4.53592f);
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public Map<String, Integer> getBaseStats() {
        return baseStats;
    }

    public void setBaseStats(Map<String, Integer> stats) {
        this.baseStats = stats;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String[]> getEvolutionChain() {
        return evolutionChain;
    }

    public void setEvolutionChain(List<String[]> evolutionChain) {
        this.evolutionChain = evolutionChain;
    }

    public List<Map<String, String>> getLocationVersions() {
        return locationVersions;
    }

    public void setLocationVersions(List<Map<String, String>> locations) {
        this.locationVersions = locations;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }
    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public PokemonMoves getMoves() {
        return moves;
    }

    public void setMoves(PokemonMoves moves) {
        this.moves = moves;
    }

    @Override
    public String toString() {
        String retString = name + " (" + id + ") \n" +
        "[weight=" + getWeightInPoundsString() + ", height=" + getHeightInFeetInches() + "] \n"+ 
        "[category=" + category + ", types=" + Arrays.toString(types) + ", base experience: " + baseExperience + "] \n" +
        "[generation=" + generation + "]\n";
        
        // Base Stats
        if (baseStats != null) createBaseStatsString();

        // Image
        retString += "[image: " + imageUrl + "] \n";

        // Description
        retString += "[description=" + description + "]\n";

        // Abilities
        if (abilities != null) retString += createAbilitiesString ();

        // Evolution Chain
        if (evolutionChain != null) retString += createEvolutionString();

        // Moves
        StringJoiner joiner = new StringJoiner(",");
        retString += createMoveString ("levelMoves", moves.getLevelMoves(), joiner);

        joiner = new StringJoiner(",");
        retString += createMoveString ("eggMoves", moves.getEggMoves(), joiner);

        joiner = new StringJoiner(",");
        retString += createMoveString ("tutorMoves", moves.getTutorMoves(), joiner);

        joiner = new StringJoiner(",");
        retString += createMoveString ("machineMoves", moves.getMachineMoves(), joiner);

        joiner = new StringJoiner(",");
        retString += createMoveString ("otherMoves", moves.getOtherMoves(), joiner);
        
        // Locations/Versions
        if (locationVersions != null) retString += createLocationString ();

        return retString;
    }

    private String createBaseStatsString () {
        String retString = "";
        retString += "[baseStats:";

        StringBuilder builder = new StringBuilder();
        for (Entry<String, Integer> stat : baseStats.entrySet()) {
            String baseStatName = stat.getKey();
            Integer baseStatNumber = stat.getValue();
            builder.append("\t" + baseStatName + ": " + baseStatNumber);
        }
        retString += builder.toString();
        retString += "] \n";
        return retString;
    }

    private String createAbilitiesString () {
        String retString = "";
        retString += "[abilities: ";
        StringBuilder builder = new StringBuilder();
        for (Ability ability : abilities) {
            builder.append("\t" + ability);
        }
        retString += builder.toString();
        retString += "] \n";
        return retString;
    }

    private String createEvolutionString () {
        String retString = "";
        retString += "[evolutionChain: ";
        StringBuilder builder = new StringBuilder();
        for (String[] evolution : evolutionChain) {
            String evolutionName = evolution[0];
            String evolutionURL = evolution[1];
            builder.append("\t" + evolutionName + ": " + evolutionURL);
        }
        retString += builder.toString();
        retString += "] \n";
        return retString;
    }

    private String createMoveString (String moveName, Set<Move> moves, StringJoiner joiner) {
        String retString = "";
        retString += "["+ moveName + ": ";
        if (moves != null && !moves.isEmpty()) {
            for (Move move : moves) {joiner.add(move.getName()); }
            retString += joiner.toString();
        }
        retString += "] \n";
        return retString;
    }

    private String createLocationString () {
        String retString = "";
        retString += "[locations: ";
        StringBuilder builder = new StringBuilder();
        for (Map<String, String> location : locationVersions) {
            String locationName = location.get("locationName");
            String locationURL = location.get("locationURL");
            String versionName = location.get("versionName");
            String maxChance = location.get("maxChance");
            String methods = location.get("methods");
            builder.append("\t\t" + locationName + ": " + locationURL + versionName + ": " + maxChance + " " + methods);
        }
        retString += builder.toString();
        retString += "] \n";
        
        return retString;
    }
}
