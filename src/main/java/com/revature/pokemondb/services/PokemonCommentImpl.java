package com.revature.pokemondb.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.PokemonComments;
import com.revature.pokemondb.repositories.PokemonCommentRepo;
import com.revature.pokemondb.utils.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonCommentImpl implements PokemonCommentService{
    private PokemonCommentRepo commentRepo;

    private ObjectMapper objectMapper;

    @Autowired
    public PokemonCommentImpl(PokemonCommentRepo commentRepo, ObjectMapper objectMapper) {
        this.commentRepo = commentRepo;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<JsonNode> getAllComments() {
        try {
            Optional<List<PokemonComments>> pokemonCommentsOptional = Optional.of(commentRepo.findAll());
            return objectMapper.valueToTree(pokemonCommentsOptional);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<PokemonComments> storeNewComment(JsonNode comment) {
        try {
            Optional<PokemonComments> pokemonCommentsOptional = commentRepo.save(objectMapper.treeToValue(comment,);
            return  pokemonCommentsOptional;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<PokemonComments> deleteComment(JsonNode comment) {
        return null;
    }

    @Override
    public Optional<PokemonComments> updateComment(JsonNode comment) {
        return null;
    }
}
