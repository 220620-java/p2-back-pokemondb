package com.revature.pokemondb.exceptions;

/**
 * Exception for when a record is not found in the database!
 * Used to prevent UPDATE and DELETE queries from happening if entry
 * is not in the database!
 * @author Colby Tang
 */
public class RecordNotFound extends Exception {

    public RecordNotFound () {
        super("Could not find record in the database!");
    }

    public RecordNotFound (Object object) {
        super("Could not find record " + object.getClass().getName() + " in the database!");
    }

    public RecordNotFound (String message) {
        super(message);
    }

}
