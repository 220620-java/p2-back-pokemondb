package com.revature.pokemondb.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentTests {
    PokemonCommentService pokeServ;


    public PokemonCommentService getPokeServ() {
        return pokeServ;
    }

    @Test
    public void testStore() {
    }
}
