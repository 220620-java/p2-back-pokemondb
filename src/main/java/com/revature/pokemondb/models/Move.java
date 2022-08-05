package com.revature.pokemondb.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Move {
    private String name;
    private String url;
    private List<Map<String, String>> versionGroupDetails;
    
    public Move () {
        this.name = "";
        this.url = "";
        this.versionGroupDetails = new ArrayList<>();
    }

    public Move (
        String name,
        String url,
        List<Map<String, String>> versionGroupDetails
        ) {
        this.name = name;
        this.url = url;
        this.versionGroupDetails = versionGroupDetails;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String abilityName) {
        this.name = abilityName;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public List<Map<String, String>> getVersionGroupDetails() {
        return versionGroupDetails;
    }

    public void setVersionGroupDetails(List<Map<String, String>> versionGroupDetails) {
        this.versionGroupDetails = versionGroupDetails;
    }

    public void addVersionGroupDetail(int levelLearnedAt, String moveLearnMethod, String versionName) {
        Map<String, String> versionGroup = new HashMap<>();
        versionGroup.put("levelLearnedAt", String.valueOf(levelLearnedAt));
        versionGroup.put("learnMethod", moveLearnMethod);
        versionGroup.put("versionName", versionName);
        versionGroupDetails.add(versionGroup);
    }
}
