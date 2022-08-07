package com.revature.pokemondb.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.pokemondb.models.dtos.FanartDTO;

@SpringBootTest
class ArtCommentTest {
    
    @Test
    void artCommentEquals() {
        ArtComment artComment = new ArtComment();
        ArtComment artComment2 = new ArtComment();
        assertEquals(artComment, artComment2);

        ArtComment artComment3 = new ArtComment(1, new FanartDTO(), new User(), "content", 2, 1, false, Date.valueOf(LocalDate.now()));
        ArtComment artComment4 = new ArtComment(1, new FanartDTO(), new User(), "content", 2, 1, false, Date.valueOf(LocalDate.now()));
        assertEquals(artComment3, artComment4);
    }
}
