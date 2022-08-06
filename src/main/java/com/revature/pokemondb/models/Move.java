package com.revature.pokemondb.models;

public class Move {
    private String name;
    private String url;
    private String typeOfMove;
    private int levelLearnedAt;

    public Move () {
        this.name = "";
        this.url = "";
        this.typeOfMove = "";
        this.levelLearnedAt = 0;
    }

    public Move (
        String name,
        String url,
        String typeOfMove,
        int levelLearnedAt
        ) {
        this.name = name;
        this.url = url;
        this.typeOfMove = typeOfMove;
        this.levelLearnedAt = levelLearnedAt;
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

    public String getTypeOfMove() {
        return typeOfMove;
    }

    public void setTypeOfMove(String typeOfMove) {
        this.typeOfMove = typeOfMove;
    }
    
    public int getLevelLearnedAt() {
        return levelLearnedAt;
    }

    public void setLevelLearnedAt(int levelLearnedAt) {
        this.levelLearnedAt = levelLearnedAt;
    }
}
