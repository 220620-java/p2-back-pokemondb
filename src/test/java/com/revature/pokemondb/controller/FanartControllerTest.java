package com.revature.pokemondb.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.revature.pokemondb.models.Fanart;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.services.FanartService;

@WebMvcTest(controllers=FanartController.class)
public class FanartControllerTest {
    @MockBean
    private FanartService fanartService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void getAllFanart() throws JsonProcessingException, Exception {
        List<Fanart> fanart = new ArrayList<>();
        Mockito.when(fanartService.getAvailableFanart()).thenReturn(fanart);

        mockMvc.perform(get("/fanart/"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(fanart)));
    }

    @Test
    void getAllFanartNull() throws JsonProcessingException, Exception {
        Mockito.when(fanartService.getAvailableFanart()).thenReturn(null);

        mockMvc.perform(get("/fanart/"))
        .andExpect(status().isNotFound());
    }

    @Test
    void getFanartById () throws JsonProcessingException, Exception {
        int id = 1;
        Fanart fanart = new Fanart();
        Mockito.when(fanartService.getFanart(id)).thenReturn(fanart);

        mockMvc.perform(get("/fanart/1"))
        .andExpect(status().isOk())
        .andExpect(content().string(objectMapper.writeValueAsString(fanart)));
    }

    @Test
    void getFanartByIdNull () throws JsonProcessingException, Exception {
        Mockito.when(fanartService.getFanart(1)).thenReturn(null);

        mockMvc.perform(get("/fanart/1"))
        .andExpect(status().isNotFound());
    }

    @Test
    void getIdLimiters() throws Exception {
        String idLimiters = "1/4";
        Mockito.when(fanartService.getLowestID()).thenReturn(1);
        Mockito.when(fanartService.getHighestID()).thenReturn(4);

        mockMvc.perform(get("/fanart/info/"))
        .andExpect(status().isOk())
        .andExpect(content().string(idLimiters));
    }

    @Test
    void getIdLimitersNegative() throws Exception {
        Mockito.when(fanartService.getLowestID()).thenReturn(-1);
        Mockito.when(fanartService.getHighestID()).thenReturn(4);

        mockMvc.perform(get("/fanart/info/"))
        .andExpect(status().isNotFound());
    }

    @Test
    void getArtInfo() throws Exception {
        Fanart fanart = new Fanart();
        Mockito.when (fanartService.getExistsById(1)).thenReturn(true);
        Mockito.when (fanartService.getFanart(1)).thenReturn(fanart);

        mockMvc.perform(get("/fanart/info/1"))
        .andExpect(status().isOk())
        .andExpect(content().string("true"));
    }

    @Test
    void getArtInfoFalse() throws Exception {
        Fanart fanart = new Fanart();
        fanart.setIsFlagged(true);
        Mockito.when (fanartService.getExistsById(1)).thenReturn(true);
        Mockito.when (fanartService.getFanart(1)).thenReturn(fanart);

        mockMvc.perform(get("/fanart/info/1"))
        .andExpect(status().isOk())
        .andExpect(content().string("false"));
    }

    @Test
    void getAvailableFanartWithTitle() throws Exception {
        List<Fanart> result = new ArrayList<Fanart>();
        Mockito.when (fanartService.getAvailableFanartWithTitle("title")).thenReturn(result);

        mockMvc.perform(get("/fanart/filters")
        .param("title", "title"))
        .andExpect(status().isOk())
        .andExpect(content().string(objectMapper.writeValueAsString(result)));
    }

    @Test
    void getAvailableFanartWithTags() throws Exception {
        List<Fanart> result = new ArrayList<Fanart>();
        Mockito.when (fanartService.getAvailableFanartWithTags("tags")).thenReturn(result);

        mockMvc.perform(get("/fanart/filters")
        .param("tags", "tags"))
        .andExpect(status().isOk())
        .andExpect(content().string(objectMapper.writeValueAsString(result)));
    }

    @Test
    void getAvailableFanartFilteredByPostDate() throws Exception {
        List<Fanart> result = new ArrayList<Fanart>();
        Mockito.when (fanartService.getAvailableFanartFilteredByPostDate(false, "dateBefore")).thenReturn(result);

        mockMvc.perform(get("/fanart/filters")
        .param("dateBefore", "dateBefore"))
        .andExpect(status().isOk())
        .andExpect(content().string(objectMapper.writeValueAsString(result)));
    }

    @Test
    void getAvailableFanartFilteredByPostDateGreater() throws Exception {
        List<Fanart> result = new ArrayList<Fanart>();
        Mockito.when (fanartService.getAvailableFanartFilteredByPostDate(true, "dateAfter")).thenReturn(result);

        mockMvc.perform(get("/fanart/filters")
        .param("dateAfter", "dateAfter"))
        .andExpect(status().isOk())
        .andExpect(content().string(objectMapper.writeValueAsString(result)));
    }

}
