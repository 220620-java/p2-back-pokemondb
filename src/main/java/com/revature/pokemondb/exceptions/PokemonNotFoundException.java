package com.revature.pokemondb.exceptions;

/**
 * Exception for when a pokemon is not found in the database!
 * @author Colby Tang
 */
public class PokemonNotFoundException extends Exception {
    public PokemonNotFoundException () {
        super("Could not find the Pokemon in the database!");
    }

    public PokemonNotFoundException (String message) {
        super(message);
    }
}
