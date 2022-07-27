package com.revature.pokemondb.models;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Pokemon {
    private int id;
    private String name;
    private int height;
    private int weight;
    private String[] types;
    private Map<String, Integer> baseStats;
    private String generation;
    private String category;
    private String description;
    private List<String[]> evolutionChain;
    private List<Map<String, String>> locations;
    private List<Map<String, String>> versions;

    public Pokemon (
        int id,
        String name,
        int height,
        int weight,
        String[] types,
        Map<String, Integer> baseStats,
        String generation,
        String category,
        String description,
        List<String[]> evolutionChain,
        List<Map<String, String>> locations,
        List<Map<String, String>> versions
    ) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.types = types;
        this.baseStats = baseStats;
        this.generation = generation;
        this.category = category;
        this.description = description;
        this.evolutionChain = evolutionChain;
        this.locations = locations;
        this.versions = versions;
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

    public void setBaseStats(Map<String, Integer> baseStats) {
        this.baseStats = baseStats;
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

    public List<Map<String, String>> getLocationArea() {
        return locations;
    }

    public void setLocationArea(List<Map<String, String>> locations) {
        this.locations = locations;
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
        for (Map<String, String> location : locations) {
            String locationName = location.get("locationName");
            String locationURL = location.get("locationURL");
            retString += "\t" + locationName + ": " + locationURL + "\n";
            retString += "\t\t";
            for (Map<String, String> version : versions) {
                String versionName = version.get("versionName");
                String maxChance = version.get("maxChance");
                retString += versionName + ": " + maxChance + ", ";
            }
            retString += "\n";
        }
        retString += "]";
        return retString;
    }

    
}
