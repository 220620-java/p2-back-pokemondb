package com.revature.pokemondb.models;

public class Ability {
    private String name;
    private String url;
    private int slot;
    private boolean isHidden;

    public Ability () {
        this.name = "";
        this.url = "";
        this.slot = 0;
        this.isHidden = false;
    }

    public Ability (
        String name,
        String url,
        int slot,
        boolean isHidden
        ) {
        this.name = name;
        this.url = url;
        this.slot = slot;
        this.isHidden = isHidden;
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
    public int getSlot() {
        return slot;
    }
    public void setSlot(int slot) {
        this.slot = slot;
    }
    public boolean isHidden() {
        return isHidden;
    }
    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    @Override
    public String toString() {
        return "Ability [name=" + name + ", url=" + url + ", isHidden=" + isHidden
                + ", slot=" + slot + "]";
    }
}
