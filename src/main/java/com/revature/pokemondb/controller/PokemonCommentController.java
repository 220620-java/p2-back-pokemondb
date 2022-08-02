package com.revature.pokemondb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.PokemonComments;
import com.revature.pokemondb.services.PokemonCommentImpl;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path="/pokemon-comment")
public class PokemonCommentController {
    private PokemonCommentImpl commentService;

    public PokemonCommentController(PokemonCommentImpl commentService){
        this.commentService = commentService;
    }

    @RequestMapping(path = "/Options", method=RequestMethod.OPTIONS)
    public ResponseEntity<?> optionsRequest () {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.PATCH, HttpMethod.OPTIONS)
                .build();
    }

    @GetMapping(path = "/flagged")
    protected ResponseEntity<String> getAllFlagged() {
        return ResponseEntity.ok(commentService.getFlagged().toString());
    }
    @GetMapping(path = "/{id}")
    protected ResponseEntity<String> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.getById(id).toString());
    }
    /*
     *
     */
    @PutMapping
    protected ResponseEntity<String> editComment(@RequestBody PokemonComments pokeComment){
        return ResponseEntity.ok(commentService.updateComment(pokeComment).toString());
    }

    /*
     *
     */
    @PostMapping
    protected ResponseEntity<String> storeComment(@RequestBody PokemonComments pokeComment) {
        ResponseEntity.ok(commentService.storeNewComment(pokeComment).toString());
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
