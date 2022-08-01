package com.revature.pokemondb.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.models.dtos.UserDTO;
import com.revature.pokemondb.services.UserService;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testOptionsRequest() throws Exception {
        mockMvc.perform(options("/user/"))
            .andExpect(status().isOk());
    }
    
    @Test
    void testGetUserById() throws Exception {
        User mockUser = new User();

        Mockito.when(userService.getUserById(1)).thenReturn(mockUser);

        mockMvc.perform(get("/user/1"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(new UserDTO(mockUser))));
    }

    @Test
    void testGetUserByIdNotFound() throws Exception {
        // If the service cannot find user
        Mockito.when(userService.getUserById(1)).thenReturn(null);

        mockMvc.perform(get("/user/1"))
        .andExpect(status().isNotFound());
    }

    @Test
    void testCreateUser() {

    }

    
    @Test
    void testPatchUserDetails() {
        
    }
    
    @Test
    void testUpdateUserDetails() {
        
    }
    
    @Test
    void testDeleteUser() {

    }
}
