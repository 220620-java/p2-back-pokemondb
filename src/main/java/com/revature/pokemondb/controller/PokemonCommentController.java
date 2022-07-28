package com.revature.pokemondb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.PokemonComments;
import com.revature.pokemondb.services.PokemonCommentImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="/pokemon")
public class PokemonCommentController {
    private PokemonCommentImpl commentService;
    private ObjectMapper JsonMapper;

    public PokemonCommentController(PokemonCommentImpl commentService, ObjectMapper JsonMapper){
        this.commentService = commentService;
        this.JsonMapper = JsonMapper;
    }

    @PutMapping
    protected ResponseEntity<String> editComment(){
     return null;
    }

    @PostMapping
    protected ResponseEntity<String> storeComment() {
        return null;
    }

    @DeleteMapping
    protected ResponseEntity<String> deleteComment() {
        return null;
    }

    @GetMapping
    protected ResponseEntity<String> getComments() {
        return null;
    }
}
