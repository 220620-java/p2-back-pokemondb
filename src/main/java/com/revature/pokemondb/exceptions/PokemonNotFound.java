package com.revature.pokemondb.exceptions;

/**
 * Exception for when a pokemon is not found in the database!
 * @author Colby Tang
 */
public class PokemonNotFound extends Exception {
    public PokemonNotFound () {
        super("Could not find the Pokemon in the database!");
    }

    public PokemonNotFound (String message) {
        super(message);
    }
}
