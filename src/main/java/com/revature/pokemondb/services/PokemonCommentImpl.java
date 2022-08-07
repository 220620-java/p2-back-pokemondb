package com.revature.pokemondb.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.Pokemon;
import com.revature.pokemondb.models.PokemonComments;
import com.revature.pokemondb.repositories.PokemonCommentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service ("PokemonCommentService")
public class PokemonCommentImpl implements PokemonCommentService {
    private PokemonCommentRepo commentRepo;

    private ObjectMapper objectMapper;

    public PokemonCommentImpl(PokemonCommentRepo commentRepo, ObjectMapper objectMapper) {
        this.commentRepo = commentRepo;
        this.objectMapper = objectMapper;
    }

    /** 
     * Get all flagged comments.
     * @return List<PokemonComments>
     */
    @Override
    public List<PokemonComments> getAllFlaggedComments() {
        return commentRepo.findAllByIsflaggedTrue();
    }

    /** 
     * Get all unflagged comments from the database.
     * @return String
     */
    @Override
    public List<PokemonComments> getAllUnflaggedComments() {
        return commentRepo.findAllByIsflaggedFalse();
    }

    /** 
     * Get all comments under a pokemon id.
     * @param id
     * @return PokemonComments
     */
    @Override
    public PokemonComments getById(Integer id) {
        return commentRepo.getReferenceById(Long.valueOf(id));
    }
    
    /** 
     * Store the new comment into the database.
     * @param comment
     * @return PokemonComments
     */
    @Override
    public PokemonComments storeNewComment(PokemonComments comment) {
        return commentRepo.save(comment);
    }
    
    /** 
     * Update the comment.
     * @param comment
     * @return PokemonComments
     */
    @Override
    public PokemonComments updateComment(PokemonComments comment) {
        return commentRepo.save(comment);
    }
    
    /** 
     * Delete the comment from the database.
     * @param comment
     */
    @Override
    public PokemonComments deleteComment(PokemonComments comment) {
        commentRepo.delete(comment);
        return comment;
    }

    public String getAllByPokemon(Pokemon pokemon) {
        try {
            Optional<List<PokemonComments>> pokemonCommentsOptional = Optional.of(commentRepo.findAllByPokemon(pokemon));
            return objectMapper.valueToTree(pokemonCommentsOptional).toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
