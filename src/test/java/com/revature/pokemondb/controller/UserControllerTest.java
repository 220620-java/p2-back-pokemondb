package com.revature.pokemondb.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.security.NoSuchAlgorithmException;
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
import com.revature.pokemondb.exceptions.EmailAlreadyExistsException;
import com.revature.pokemondb.exceptions.InvalidInputException;
import com.revature.pokemondb.exceptions.RecordNotFoundException;
import com.revature.pokemondb.exceptions.UsernameAlreadyExistsException;
import com.revature.pokemondb.models.BannedUser;
import com.revature.pokemondb.models.User;
import com.revature.pokemondb.models.dtos.UserDTO;
import com.revature.pokemondb.services.UserService;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    
    /** 
     * @throws Exception
     */
    @Test
    void testOptionsRequest() throws Exception {
        mockMvc.perform(options("/user/"))
            .andExpect(status().isOk());
    }

    
    /** 
     * @throws Exception
     */
    @Test
    void testGetUserById() throws Exception {
        User mockUser = new User();
        Mockito.when(userService.getUserById(1l)).thenReturn(mockUser);
        mockMvc.perform(get("/user/1"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(new UserDTO(mockUser))));
    }

    
    /** 
     * @throws Exception
     */
    @Test
    void testGetUserByIdNotFound() throws Exception {
        // If the service cannot find user
        Mockito.when(userService.getUserById(1l)).thenThrow(RecordNotFoundException.class);

        mockMvc.perform(get("/user/1"))
        .andExpect(status().isNotFound());
    }

    
    /** 
     * @throws Exception
     */
    @Test
    void testGetUserByUsername() throws Exception {
        User mockUser = new User();
        Mockito.when(userService.getUserByUsername("user")).thenReturn(mockUser);
        mockMvc.perform(get("/user/user"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(new UserDTO(mockUser))));
    }

    
    /** 
     * @throws Exception
     */
    @Test
    void testGetUserByUsernameNotFound() throws Exception {
        Mockito.when(userService.getUserByUsername("user2")).thenThrow(RecordNotFoundException.class);
        mockMvc.perform(get("/user/user2"))
        .andExpect(status().isNotFound());
    }

    
    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    void testGetAllUsers() throws JsonProcessingException, Exception {
        List<User> mockUsers = new ArrayList<>();
        User mockUser = new User();
        mockUsers.add(mockUser);
        Mockito.when(userService.getAllUsers()).thenReturn(mockUsers);
        List<UserDTO> allUsersDTO = new ArrayList<>();
        for (User user : mockUsers) {
            allUsersDTO.add(new UserDTO(user));
        }
        mockMvc.perform(get("/user/"))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(allUsersDTO)));
    }

    
    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    void testGetAllUsersNotFound() throws JsonProcessingException, Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(null);
        mockMvc.perform(get("/user/"))
        .andExpect(status().isNotFound());
    }

    
    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
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

    
    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
	void registerUserAlreadyExists() throws JsonProcessingException, Exception {
		User mockUser = new User();
		
		Mockito.when(userService.registerUser(mockUser)).thenThrow(UsernameAlreadyExistsException.class);
		
		mockMvc.perform(post("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isConflict());
	}

    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
	void registerUserEmptyPassword() throws JsonProcessingException, Exception {
		User mockUser = new User();
        mockUser.setPassword(null);
		
		Mockito.when(userService.registerUser(mockUser)).thenThrow(InvalidInputException.class);
		
		mockMvc.perform(post("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isBadRequest());
	}
	
    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
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

    
    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    void updateUserNotFound() throws JsonProcessingException, Exception {
		User mockUser = new User();
		mockUser.setUserId(1l);
		
		Mockito.when(userService.updateUser(mockUser)).thenThrow(RecordNotFoundException.class);
		
		mockMvc.perform(put("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isNotFound());
	}

    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    void updateUserWrongAlgorithm() throws JsonProcessingException, Exception {
		User mockUser = new User();
		mockUser.setUserId(1l);
		
		Mockito.when(userService.updateUser(mockUser)).thenThrow(NoSuchAlgorithmException.class);
		
		mockMvc.perform(put("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isBadRequest());
	}

    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    void updateUserEmailAlreadyExistsException() throws JsonProcessingException, Exception {
		User mockUser = new User();
		mockUser.setUserId(1l);
		
		Mockito.when(userService.updateUser(mockUser)).thenThrow(EmailAlreadyExistsException.class);
		
		mockMvc.perform(put("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isConflict());
	}
    
    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
	void updateUserNull() throws JsonProcessingException, Exception {
		User mockUser = new User();
		mockUser.setUserId(1l);
		
		Mockito.when(userService.updateUser(mockUser)).thenReturn(null);
		
		mockMvc.perform(put("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isBadRequest());
	}

    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
	void patchUserSuccess() throws JsonProcessingException, Exception {
		User mockUser = new User();
		mockUser.setUserId(1l);
		
		Mockito.when(userService.updateUser(mockUser)).thenReturn(mockUser);
		
		mockMvc.perform(patch("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(new UserDTO(mockUser))));
	}

    
    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    void patchUserNotFound() throws JsonProcessingException, Exception {
		User mockUser = new User();
		mockUser.setUserId(1l);
		
		Mockito.when(userService.updateUser(mockUser)).thenThrow(RecordNotFoundException.class);
		
		mockMvc.perform(patch("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isNotFound());
	}

    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    void patchUserWrongAlgorithm() throws JsonProcessingException, Exception {
		User mockUser = new User();
		mockUser.setUserId(1l);
		
		Mockito.when(userService.updateUser(mockUser)).thenThrow(NoSuchAlgorithmException.class);
		
		mockMvc.perform(patch("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isBadRequest());
	}

    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    void patchUserEmailAlreadyExistsException() throws JsonProcessingException, Exception {
		User mockUser = new User();
		mockUser.setUserId(1l);
		
		Mockito.when(userService.updateUser(mockUser)).thenThrow(EmailAlreadyExistsException.class);
		
		mockMvc.perform(patch("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isConflict());
	}
    
    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
	void patchUserNull() throws JsonProcessingException, Exception {
		User mockUser = new User();
		mockUser.setUserId(1l);
		
		Mockito.when(userService.updateUser(mockUser)).thenReturn(null);
		
		mockMvc.perform(patch("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isBadRequest());
	}

	
    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
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

    
    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
	void deleteUserNotFound() throws JsonProcessingException, Exception {
		User mockUser = new User();
		mockUser.setUserId(1l);
		
		Mockito.when(userService.deleteUser(mockUser)).thenThrow(RecordNotFoundException.class);
		
		mockMvc.perform(delete("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isBadRequest());
	}

    
    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
	void deleteUserNull() throws JsonProcessingException, Exception {
		User mockUser = new User();
		mockUser.setUserId(1l);
		
		Mockito.when(userService.deleteUser(mockUser)).thenReturn(null);
		
		mockMvc.perform(delete("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(mockUser)))
			.andExpect(status().isBadRequest());
	}

    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    void banUser() throws JsonProcessingException, Exception {
        BannedUser banBody = new BannedUser(1l);
		User mockUserWithId = new User();
        mockUserWithId.setUserId(1l);
        Mockito.when(userService.banUser(banBody)).thenReturn(mockUserWithId);
        UserDTO userDTO = new UserDTO(mockUserWithId);
        mockMvc.perform(post("/user/ban")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(banBody)))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(userDTO)));
    }

    /** 
     * @throws UsernameAlreadyExistsException
     * @throws Exception
     */
    @Test
    void banUserAlreadyBanned() throws UsernameAlreadyExistsException, Exception {
        BannedUser banBody = new BannedUser(1l);
		User mockUserWithId = new User();
        mockUserWithId.setUserId(1l);
        Mockito.when(userService.banUser(banBody)).thenThrow(UsernameAlreadyExistsException.class);
        mockMvc.perform(post("/user/ban")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(banBody)))
            .andExpect(status().isBadRequest());
    }


    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    void unBanUser() throws JsonProcessingException, Exception {
		User mockUserWithId = new User();
        mockUserWithId.setUserId(1l);
        Mockito.when(userService.unBanUser(1l)).thenReturn(mockUserWithId);
        UserDTO userDTO = new UserDTO(mockUserWithId);
        mockMvc.perform(post("/user/unban/1"))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(userDTO)));
    }

    /** 
     * This would probably never happen
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    void unBanUserNull() throws RecordNotFoundException, JsonProcessingException, Exception {
		User mockUserWithId = new User();
        mockUserWithId.setUserId(1l);
        Mockito.when(userService.unBanUser(1l)).thenReturn(null);
        mockMvc.perform(post("/user/unban/1"))
            .andExpect(status().isNotFound());
    }

    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    void unBanUserNotFound() throws RecordNotFoundException, JsonProcessingException, Exception {
		User mockUserWithId = new User();
        mockUserWithId.setUserId(1l);
        Mockito.when(userService.unBanUser(1l)).thenThrow(RecordNotFoundException.class);
        mockMvc.perform(post("/user/unban/1"))
            .andExpect(status().isNotFound());
    }

    /** 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    void unBanUserWrongFormat() throws NumberFormatException, JsonProcessingException, Exception {
		User mockUserWithId = new User();
        mockUserWithId.setUserId(1l);
        Mockito.when(userService.unBanUser(1l)).thenThrow(NumberFormatException.class);
        mockMvc.perform(post("/user/unban/1"))
            .andExpect(status().isNotFound());
    }
}
