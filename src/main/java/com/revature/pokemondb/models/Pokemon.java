package com.revature.pokemondb.models;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.Map.Entry;

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
    private Set<Move> levelMoves;

    @Transient
    private Set<Move> eggMoves;
    
    @Transient
    private Set<Move> tutorMoves;
    
    @Transient
    private Set<Move> machineMoves;
    
    @Transient
    private Set<Move> otherMoves;

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
        this.levelMoves = new HashSet<>();
        this.eggMoves = new HashSet<>();
        this.tutorMoves = new HashSet<>();
        this.machineMoves = new HashSet<>();
        this.otherMoves = new HashSet<>();
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
        this.levelMoves = new HashSet<>();
        this.eggMoves = new HashSet<>();
        this.tutorMoves = new HashSet<>();
        this.machineMoves = new HashSet<>();
        this.otherMoves = new HashSet<>();
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
        Set<Move> levelMoves,
        Set<Move> eggMoves,
        Set<Move> tutorMoves,
        Set<Move> machineMoves,
        Set<Move> otherMoves
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
        this.levelMoves = levelMoves;
        this.eggMoves = eggMoves;
        this.tutorMoves = tutorMoves;
        this.machineMoves = machineMoves;
        this.otherMoves = otherMoves;
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

    public Set<Move> getLevelMoves() {
        return levelMoves;
    }

    public void setLevelMoves(Set<Move> levelMoves) {
        this.levelMoves = levelMoves;
    }

    public Set<Move> getEggMoves() {
        return eggMoves;
    }

    public void setEggMoves(Set<Move> eggMoves) {
        this.eggMoves = eggMoves;
    }

    public Set<Move> getTutorMoves() {
        return tutorMoves;
    }

    public void setTutorMoves(Set<Move> tutorMoves) {
        this.tutorMoves = tutorMoves;
    }
    
    public Set<Move> getMachineMoves() {
        return machineMoves;
    }

    public void setMachineMoves(Set<Move> machineMoves) {
        this.machineMoves = machineMoves;
    }

    public Set<Move> getOtherMoves() {
        return otherMoves;
    }

    public void setOtherMoves(Set<Move> otherMoves) {
        this.otherMoves = otherMoves;
    }

    @Override
    public String toString() {
        String retString = name + " (" + id + ") \n" +
        "[weight=" + getWeightInPoundsString() + ", height=" + getHeightInFeetInches() + "] \n"+ 
        "[category=" + category + ", types=" + Arrays.toString(types) + ", base experience: " + baseExperience + "] \n" +
        "[generation=" + generation + "]\n";
        
        // Base Stats
        retString += "[baseStats:";
        if (baseStats != null) {
            StringBuilder builder = new StringBuilder();
            for (Entry<String, Integer> stat : baseStats.entrySet()) {
                String baseStatName = stat.getKey();
                Integer baseStatNumber = stat.getValue();
                builder.append("\t" + baseStatName + ": " + baseStatNumber);
            }
            retString += builder.toString();
        }
        retString += "] \n";

        // Image
        retString += "[image: " + imageUrl + "] \n";

        // Description
        retString += "[description=" + description + "]\n";

        // Abilities
        retString += "[abilities: ";
        if (abilities != null) {
            StringBuilder builder = new StringBuilder();
            for (Ability ability : abilities) {
                builder.append("\t" + ability);
            }
            retString += builder.toString();
        }
        retString += "] \n";

        // Evolution Chain
        retString += "[evolutionChain: ";
        if (evolutionChain != null) {
            StringBuilder builder = new StringBuilder();
            for (String[] evolution : evolutionChain) {
                String evolutionName = evolution[0];
                String evolutionURL = evolution[1];
                builder.append("\t" + evolutionName + ": " + evolutionURL);
            }
            retString += builder.toString();
        }
        retString += "] \n";

        // Moves
        StringJoiner joiner = new StringJoiner(",");

        retString += "[levelMoves: ";
        if (levelMoves != null && !levelMoves.isEmpty()) {
            for (Move move : levelMoves) { joiner.add(move.getName()); }
            retString += joiner.toString();
        }
        retString += "] \n";

        joiner = new StringJoiner(",");
        retString += "[eggMoves: ";
        if (eggMoves != null && !eggMoves.isEmpty()) {
            for (Move move : eggMoves) { joiner.add(move.getName()); }
            retString += joiner.toString();
        }
        retString += "] \n";

        joiner = new StringJoiner(",");
        retString += "[tutorMoves: ";
        if (tutorMoves != null && !tutorMoves.isEmpty()) {
            for (Move move : tutorMoves) { joiner.add(move.getName()); }
            retString += joiner.toString();
        }
        retString += "] \n";

        joiner = new StringJoiner(",");
        retString += "[machineMoves: ";
        if (machineMoves != null && !machineMoves.isEmpty()) {
            for (Move move : machineMoves) { joiner.add(move.getName()); }
            retString += joiner.toString();
        }
        retString += "] \n";

        joiner = new StringJoiner(",");
        retString += "[otherMoves: ";
        if (otherMoves != null && !otherMoves.isEmpty()) {
            for (Move move : otherMoves) { joiner.add(move.getName()); }
            retString += joiner.toString();
        }
        retString += "] \n";
        
        // Locations/Versions
        retString += "[locations: ";
        if (locationVersions != null) {
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
        }
        retString += "] \n";
        
        return retString;
    }
}
