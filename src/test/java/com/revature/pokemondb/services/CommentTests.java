package com.revature.pokemondb.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.pokemondb.PokemondbApplication;

@SpringBootTest(classes=PokemondbApplication.class)
public class CommentTests {

    @Autowired
    private PokemonCommentService pokeServ;

    // @Test
    // void testStore() {
    // }
}
