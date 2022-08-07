package com.revature.pokemondb.models;

import java.util.HashSet;
import java.util.Set;

public class PokemonMoves {
    private Set<Move> levelMoves;

    private Set<Move> eggMoves;
    
    private Set<Move> tutorMoves;
    
    private Set<Move> machineMoves;
    
    private Set<Move> otherMoves;

    public PokemonMoves() {
        this.levelMoves = new HashSet<>();
        this.eggMoves = new HashSet<>();
        this.tutorMoves = new HashSet<>();
        this.machineMoves = new HashSet<>();
        this.otherMoves = new HashSet<>();
    }

    public PokemonMoves(Set<Move> levelMoves, Set<Move> eggMoves, Set<Move> tutorMoves, Set<Move> machineMoves,
            Set<Move> otherMoves) {
        this.levelMoves = levelMoves;
        this.eggMoves = eggMoves;
        this.tutorMoves = tutorMoves;
        this.machineMoves = machineMoves;
        this.otherMoves = otherMoves;
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

    public Set<Move> getAllMoves () {
        Set<Move> retMoves = new HashSet<>();
        retMoves.addAll(levelMoves);
        retMoves.addAll(eggMoves);
        retMoves.addAll(tutorMoves);
        retMoves.addAll(machineMoves);
        retMoves.addAll(otherMoves);
        return retMoves;
    }
}
