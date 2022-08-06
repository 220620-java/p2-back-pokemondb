package com.revature.pokemondb.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.pokemondb.models.PokemonComments;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.repositories.PokemonCommentRepo;

@SpringBootTest
public class PokemonCommentImplTest {

    @MockBean
    private PokemonCommentRepo commentRepo;

    @Autowired
    private PokemonCommentService commentService;

    @Test
    void testGetById() {
        Integer id = 1;
        PokemonComments comments = new PokemonComments(Long.valueOf(id), new Pokemon(2), new User(1l), "comment_content", false, 5);
        Mockito.when (commentRepo.getReferenceById(Long.valueOf(id))).thenReturn(comments);
        assertNotNull(commentService.getById(id));
    }
    
    @Test
    void getAllUnflaggedComments() {
        List<PokemonComments> comments = new ArrayList<>();
        Mockito.when (commentRepo.findAllByIsflaggedFalse()).thenReturn(comments);
        assertNotNull(commentService.getAllUnflaggedComments());
    }
    
    
    @Test
    void testGetFlagged() {
        List<PokemonComments> comments = new ArrayList<>();
        Mockito.when (commentRepo.findAllByIsflaggedFalse()).thenReturn(comments);
        assertNotNull(commentService.getAllFlaggedComments());
    }

    @Test
    void testStoreNewComment() {
        PokemonComments comment = new PokemonComments(Long.valueOf(1), new Pokemon(2), new User(1l), "comment_content", false, 5);
        Mockito.when (commentRepo.save(comment)).thenReturn(comment);
        assertNotNull(commentService.storeNewComment(comment));
    }
    
    @Test
    void testUpdateComment() {
        PokemonComments comment = new PokemonComments(Long.valueOf(1), new Pokemon(2), new User(1l), "comment_content", false, 5);
        Mockito.when (commentRepo.save(comment)).thenReturn(comment);
        assertNotNull(commentService.updateComment(comment));
    }

    @Test
    void testDeleteComment() {
        PokemonComments comment = new PokemonComments(Long.valueOf(1), new Pokemon(2), new User(1l), "comment_content", false, 5);
        assertNotNull(commentService.deleteComment(comment));
    }
}
