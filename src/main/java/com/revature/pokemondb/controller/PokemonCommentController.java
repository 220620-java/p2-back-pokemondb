package com.revature.pokemondb.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.models.PokemonComments;
import com.revature.pokemondb.services.PokemonCommentImpl;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping(path="/pokemon-comment")
public class PokemonCommentController {
    private final PokemonCommentImpl commentService;
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
    protected ResponseEntity<String> editComment(@RequestBody PokemonComments pokeComment) {
            return ResponseEntity.ok(commentService.updateComment(pokeComment).toString());
    }

    @GetMapping(path = "/all{id}")
    protected ResponseEntity<String> getAllById(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.getAllByPokemon(new Pokemon(id)));
    }

    /*
     *
     */
    @PostMapping
    protected ResponseEntity<String> storeComment(@RequestBody PokemonComments pokeComment) throws IOException {
            return ResponseEntity.ok(objectMapper.writeValueAsString(objectMapper.valueToTree(commentService.storeNewComment(pokeComment))));
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
            return ResponseEntity.ok(commentService.getAllComments());
        }
    }

