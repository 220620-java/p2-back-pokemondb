package com.revature.pokemondb.exceptions;

public class BannedException extends Exception {
    public BannedException () {
        super ("Unauthorized! User is banned.");
    }
}
