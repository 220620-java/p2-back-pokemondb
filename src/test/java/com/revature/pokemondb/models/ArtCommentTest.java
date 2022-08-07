package com.revature.pokemondb.models;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.pokemondb.models.dtos.FanartDTO;
import com.revature.pokemondb.models.dtos.UserIdDTO;

@SpringBootTest
class ArtCommentTest {
    
    @Test
    void artCommentEquals() {
        ArtComment artComment = new ArtComment();
        ArtComment artComment2 = new ArtComment();
        assertEquals(artComment, artComment2);
        Date now = Date.valueOf(LocalDate.now());
        ArtComment artComment3 = new ArtComment(1, new FanartDTO(), new UserIdDTO(), "content", 2, 1, false, now);
        assertNotNull(artComment3);
    }
}
