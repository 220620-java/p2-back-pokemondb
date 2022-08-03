package com.revature.pokemondb.exceptions;

/**
 * Exception for when a record is not found in the database!
 * Used to prevent UPDATE and DELETE queries from happening if entry
 * is not in the database!
 * @author Colby Tang
 */
public class RecordNotFoundException extends Exception {

    public RecordNotFoundException () {
        super("Could not find record in the database!");
    }

    public RecordNotFoundException (Object object) {
        super("Could not find record " + object.getClass().getName() + " in the database!");
    }

    public RecordNotFoundException (String message) {
        super(message);
    }

}
