package com.revature.pokemondb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.PokemonComments;
import com.revature.pokemondb.services.PokemonCommentImpl;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="/pokemon-comment")
public class PokemonCommentController {
    private PokemonCommentImpl commentService;

    public PokemonCommentController(PokemonCommentImpl commentService){
        this.commentService = commentService;
    }

    public ResponseEntity<?> optionsRequest () {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.PATCH, HttpMethod.OPTIONS)
                .build();
    }
    /*
     *
     */
    @PutMapping
    protected ResponseEntity<String> editComment(@RequestBody PokemonComments pokeComment){
        commentService.updateComment(pokeComment);
     return null;
    }

    /*
     *
     */
    @PostMapping
    protected ResponseEntity<String> storeComment(@RequestBody PokemonComments pokeComment) {
        commentService.storeNewComment(pokeComment);
        return null;
    }

    /*
     *
     */
    @DeleteMapping
    protected ResponseEntity<String> deleteComment(@RequestBody PokemonComments pokeComment) {
        commentService.deleteComment(pokeComment);
        return null;
    }

    /*
     *
     */
    @GetMapping
    protected ResponseEntity<String> getComments() {
        return ResponseEntity.ok(commentService.getAllComments().toString());
    }
}
