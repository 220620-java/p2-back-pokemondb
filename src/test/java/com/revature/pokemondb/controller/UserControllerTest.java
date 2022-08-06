package com.revature.pokemondb.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokemondb.exceptions.RecordNotFoundException;
import com.revature.pokemondb.exceptions.UsernameAlreadyExistsException;
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
        Mockito.when(userService.getUserById(1l)).thenReturn(mockUser);
        mockMvc.perform(get("/user/1"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(new UserDTO(mockUser))));
    }

    @Test
    void testGetUserByIdNotFound() throws Exception {
        // If the service cannot find user
        Mockito.when(userService.getUserById(1l)).thenThrow(RecordNotFoundException.class);

        mockMvc.perform(get("/user/1"))
        .andExpect(status().isNotFound());
    }

    @Test
    void testGetUserByUsername() throws Exception {
        User mockUser = new User();
        Mockito.when(userService.getUserByUsername("user")).thenReturn(mockUser);
        mockMvc.perform(get("/user/user"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(new UserDTO(mockUser))));
    }

    @Test
    void testGetUserByUsernameNotFound() throws Exception {
        Mockito.when(userService.getUserByUsername("user2")).thenThrow(RecordNotFoundException.class);
        mockMvc.perform(get("/user/user2"))
        .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllUsers() throws JsonProcessingException, Exception {
        List<User> mockUsers = new ArrayList<>();
        Mockito.when(userService.getAllUsers()).thenReturn(mockUsers);
        List<UserDTO> allUsersDTO = new ArrayList<>();
        for (User user : mockUsers) {
            allUsersDTO.add(new UserDTO(user));
        }
        mockMvc.perform(get("/user/"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(allUsersDTO)));
    }

    @Test
    void testGetAllUsersNotFound() throws JsonProcessingException, Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(null);
        mockMvc.perform(get("/user/"))
        .andExpect(status().isNotFound());
    }

    @Test
    void testCreateUserSuccess() throws JsonProcessingException, Exception {
        User mockUser = new User();
		User mockUserWithId = new User();
        mockUserWithId.setUserId(1l);
        Mockito.when(userService.registerUser(mockUser)).thenReturn(mockUserWithId);
        UserDTO userDTO = new UserDTO(mockUserWithId);
        mockMvc.perform(post("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
            .andExpect(status().isCreated())
            .andExpect(content().json(objectMapper.writeValueAsString(userDTO)));
    }

    @Test
	void registerUserAlreadyExists() throws JsonProcessingException, Exception {
		User mockUser = new User();
		
		Mockito.when(userService.registerUser(mockUser)).thenThrow(UsernameAlreadyExistsException.class);
		
		mockMvc.perform(post("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isConflict());
	}
    
	@Test
	void updateUserSuccess() throws JsonProcessingException, Exception {
		User mockUser = new User();
		mockUser.setUserId(1l);
		
		Mockito.when(userService.updateUser(mockUser)).thenReturn(mockUser);
		
		mockMvc.perform(put("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(new UserDTO(mockUser))));
	}

    @Test
	public void updateUserNotFound() throws JsonProcessingException, Exception {
		User mockUser = new User();
		mockUser.setUserId(1l);
		
		Mockito.when(userService.updateUser(mockUser)).thenThrow(RecordNotFoundException.class);
		
		mockMvc.perform(put("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isBadRequest());
	}

    @Test
	public void updateUserNull() throws JsonProcessingException, Exception {
		User mockUser = new User();
		mockUser.setUserId(1l);
		
		Mockito.when(userService.updateUser(mockUser)).thenReturn(null);
		
		mockMvc.perform(put("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isBadRequest());
	}
    
	@Test
	void deleteUserSuccess() throws JsonProcessingException, Exception {
		User mockUser = new User();
		mockUser.setUserId(1l);
		
		Mockito.when(userService.deleteUser(mockUser)).thenReturn(mockUser);
		
		mockMvc.perform(delete("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(new UserDTO(mockUser))));
	}

    @Test
	public void deleteUserNotFound() throws JsonProcessingException, Exception {
		User mockUser = new User();
		mockUser.setUserId(1l);
		
		Mockito.when(userService.deleteUser(mockUser)).thenThrow(RecordNotFoundException.class);
		
		mockMvc.perform(delete("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isBadRequest());
	}

    @Test
	public void deleteUserNull() throws JsonProcessingException, Exception {
		User mockUser = new User();
		mockUser.setUserId(1l);
		
		Mockito.when(userService.deleteUser(mockUser)).thenReturn(null);
		
		mockMvc.perform(delete("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isBadRequest());
	}
}
