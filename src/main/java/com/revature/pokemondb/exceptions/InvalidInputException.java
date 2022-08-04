package com.revature.pokemondb.exceptions;

/**
 * Exception for invalid user input.
 */
public class InvalidInputException extends Exception {
    public InvalidInputException () {
        super ("Invalid input is given!");
    }
}
