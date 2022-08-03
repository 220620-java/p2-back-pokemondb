package com.revature.pokemondb.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.PokemonComments;
import com.revature.pokemondb.repositories.PokemonCommentRepo;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonCommentImpl implements PokemonCommentService {
    private PokemonCommentRepo commentRepo;

    private ObjectMapper objectMapper;

    @Autowired
    public PokemonCommentImpl(PokemonCommentRepo commentRepo, ObjectMapper objectMapper) {
        this.commentRepo = commentRepo;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getAllComments() {
        try {
            Optional<List<PokemonComments>> pokemonCommentsOptional = Optional.of(commentRepo.findAllByIsflaggedFalse());
            return objectMapper.valueToTree(pokemonCommentsOptional).toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PokemonComments storeNewComment(PokemonComments comment) {
        return commentRepo.save(comment);

    }

    @Override
    public void deleteComment(PokemonComments comment) {
        try {
            commentRepo.delete(comment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<PokemonComments> updateComment(PokemonComments comment) {
        return getPokemonComments(objectMapper.valueToTree(comment));
    }

    public List<PokemonComments> getFlagged() {
        return commentRepo.findAllByIsflaggedTrue();
    }

    public PokemonComments getById(Integer id) {
        return commentRepo.getReferenceById(Long.valueOf(id));
    }

    @Nullable
    private Optional<PokemonComments> getPokemonComments(JsonNode comment) {
        PokemonComments pokemonComments = new PokemonComments();
        pokemonComments.getClass();
        try {
            PokemonComments pokemonCommentsOptional = commentRepo.save(objectMapper.treeToValue(comment,pokemonComments.getClass()));
            return Optional.of(pokemonCommentsOptional);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
