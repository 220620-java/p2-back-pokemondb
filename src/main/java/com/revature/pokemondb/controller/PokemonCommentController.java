package com.revature.pokemondb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    private final ObjectMapper objectMapper;

    public PokemonCommentController(PokemonCommentImpl commentService, ObjectMapper objectMapper){
        this.commentService = commentService;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(path = "/Options", method=RequestMethod.OPTIONS)
    public ResponseEntity<?> optionsRequest () {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.PATCH, HttpMethod.OPTIONS)
                .build();
    }

    @GetMapping(path = "/flagged")
    protected ResponseEntity<String> getAllFlagged() throws JsonProcessingException {
        PokemonComments pokemonComments = new PokemonComments();
        return ResponseEntity.ok(objectMapper.writeValueAsString(objectMapper.valueToTree(pokemonComments.getClass())));
    }
    @GetMapping(path = "/{id}")
    protected ResponseEntity<String> findById(@PathVariable Integer id) throws JsonProcessingException {
        PokemonComments pokemonComments = new PokemonComments(id.longValue());
        return ResponseEntity.ok(objectMapper.writeValueAsString(objectMapper.valueToTree(pokemonComments.getClass())));
    }
    /*
     *
     */
    @PutMapping
    protected ResponseEntity<String> editComment(@RequestBody PokemonComments pokeComment) {
        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(objectMapper.valueToTree(pokeComment.getClass())));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     *
     */
    @PostMapping
    protected ResponseEntity<String> storeComment(@RequestBody PokemonComments pokeComment) {
        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(objectMapper.valueToTree(pokeComment.getClass())));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     *
     */
    @DeleteMapping
    protected ResponseEntity<String> deleteComment(@RequestBody PokemonComments pokeComment) {
        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(objectMapper.valueToTree(pokeComment.getClass())));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     *
     */
    @GetMapping
    protected ResponseEntity<String> getComments() {
        PokemonComments pokemonComments = new PokemonComments();
        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(objectMapper.valueToTree(pokemonComments.getClass())));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
