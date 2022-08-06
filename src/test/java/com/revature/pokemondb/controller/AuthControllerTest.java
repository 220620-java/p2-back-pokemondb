package com.revature.pokemondb.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.exceptions.BannedException;
import com.revature.pokemondb.exceptions.FailedAuthenticationException;
import com.revature.pokemondb.exceptions.RecordNotFoundException;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.models.dtos.UserDTO;
import com.revature.pokemondb.services.TokenService;
import com.revature.pokemondb.services.UserService;

@WebMvcTest(controllers=AuthController.class)
class AuthControllerTest {
    @MockBean
    private UserService userService;

    @MockBean
    private TokenService tokenService;
    
    @Autowired 
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    
    /** 
     * @throws Exception
     */
    @Test
    void validateToken () throws Exception {
        mockMvc.perform(get("/auth"))
        .andExpect(status().isOk());
    }

    
    /** 
     * @throws Exception
     */
    @Test
    void login () throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", "user");
        map.put("password", "pass");
        
        User mockUser = new User(map);
        UserDTO mockDto = new UserDTO(mockUser);
        mockDto.setToken("token");
        Mockito.when (userService.loginUser("user", "pass")).thenReturn(mockUser);
        Mockito.when (tokenService.createToken(mockUser)).thenReturn("token");
        mockMvc.perform(post("/auth")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(map)))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(mockDto)));
    }

    
    /** 
     * @throws Exception
     */
    @Test
    void loginNullUser () throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", "user");
        map.put("password", "pass");
        
        Mockito.when (userService.loginUser("user", "pass")).thenReturn(null);
        
        mockMvc.perform(post("/auth")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(map)))
        .andExpect(status().isUnauthorized());
    }

    
    /** 
     * @throws Exception
     */
    @Test
    void loginFailAuth () throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", "user");
        map.put("password", "pass");
        
        User mockUser = new User(map);
        UserDTO mockDto = new UserDTO(mockUser);
        mockDto.setToken("token");
        Mockito.when (userService.loginUser("user", "pass")).thenThrow(FailedAuthenticationException.class);
        mockMvc.perform(post("/auth")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(map)))
        .andExpect(status().isUnauthorized());
    }

    
    /** 
     * @throws Exception
     */
    @Test
    void loginWrongAlgorithm () throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", "user");
        map.put("password", "pass");
        
        User mockUser = new User(map);
        UserDTO mockDto = new UserDTO(mockUser);
        mockDto.setToken("token");
        Mockito.when (userService.loginUser("user", "pass")).thenThrow(NoSuchAlgorithmException.class);
        mockMvc.perform(post("/auth")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(map)))
        .andExpect(status().isUnauthorized());
    }

    
    /** 
     * @throws Exception
     */
    @Test
    void loginNotFound () throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", "user");
        map.put("password", "pass");
        
        User mockUser = new User(map);
        UserDTO mockDto = new UserDTO(mockUser);
        mockDto.setToken("token");
        Mockito.when (userService.loginUser("user", "pass")).thenThrow(RecordNotFoundException.class);
        mockMvc.perform(post("/auth")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(map)))
        .andExpect(status().isUnauthorized());
    }

    
    /** 
     * @throws Exception
     */
    @Test
    void loginBanned () throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", "user");
        map.put("password", "pass");
        
        User mockUser = new User(map);
        UserDTO mockDto = new UserDTO(mockUser);
        mockDto.setToken("token");
        Mockito.when (userService.loginUser("user", "pass")).thenThrow(BannedException.class);
        mockMvc.perform(post("/auth")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(map)))
        .andExpect(status().isForbidden());
    }
}
