package com.revature.pokemondb.models;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Entity
@Table(name= "pokemon", schema="pokemon_db")
public class Pokemon {
	
	@Id
	@Column
    private int id;
	@Column(name="pokemon")
    private String name;
	@Transient 
    private int height;
	@Transient
    private int weight;
	@Transient
	private String[] types;
	@Transient
	private Map<String, Integer> baseStats;
	@Transient
	private String imageUrl;
	@Transient
    private String generation;
	@Transient
    private String category;
	@Transient
    private String description;
	@Transient
    private List<String[]> evolutionChain;
    @Transient
    private List<Map<String, String>> location_versions;

    public Pokemon (
        int id,
        String name,
        int height,
        int weight,
        String[] types,
        Map<String, Integer> baseStats,
        String imageUrl,
        String generation,
        String category,
        String description,
        List<String[]> evolutionChain,
        List<Map<String, String>> location_versions
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
        this.location_versions = location_versions;
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
        float heightInInches = getHeight() * 3.937f;
        return heightInInches;
    }

    public float getHeightInFeet() {
        float heightInFeet = getHeight() * 0.328084f;
        return heightInFeet;
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
        float pounds = weight / 4.536f;
        return pounds;
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

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
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
        return location_versions;
    }

    public void setLocationVersions(List<Map<String, String>> locations) {
        this.location_versions = locations;
    }

    @Override
    public String toString() {
        String retString = name + " (" + id + ") \n" +
        "[weight=" + getWeightInPoundsString() + ", height=" + getHeightInFeetInches() + "] \n"+ 
        "[category=" + category + ", types=" + Arrays.toString(types) + "] \n" +
        "[generation=" + generation + "] \n";
        
        // Base Stats
        retString += "[baseStats: \n";
        for (String stat : baseStats.keySet()) {
            String baseStatName = stat;
            Integer baseStatNumber = baseStats.get(stat);
            retString += "\t" + baseStatName + ": " + baseStatNumber + "\n";
        }
        retString += "] \n";

        // Image
        retString += "[image: " + imageUrl + "] \n";

        // Description
        retString += "[description=" + description + "]\n";

        // Evolution Chain
        retString += "[evolutionChain: \n";
        for (String[] evolution : evolutionChain) {
            String evolutionName = evolution[0];
            String evolutionURL = evolution[1];
            retString += "\t" + evolutionName + ": " + evolutionURL + "\n";
        }
        retString += "] \n";
        
        // Locations/Versions
        retString += "[locations: \n";
        for (Map<String, String> location : location_versions) {
            String locationName = location.get("locationName");
            String locationURL = location.get("locationURL");
            String versionName = location.get("versionName");
            String maxChance = location.get("maxChance");
            String methods = location.get("methods");
            retString += "\t" + locationName + ": " + locationURL + "\n";
            retString += "\t\t" + versionName + ": " + maxChance + " " + methods;
            retString += "\n";
        }
        retString += "]";
        return retString;
    }
}
