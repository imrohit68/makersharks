package com.example.makersharksassessment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.makersharksassessment.Controller.UserController;
import com.example.makersharksassessment.DTO.UsersDTO;
import com.example.makersharksassessment.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class RegisterUserTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Autowired
    private ObjectMapper objectMapper;

    private UsersDTO usersDTO;

    @BeforeEach
    void setUp() {
        usersDTO = new UsersDTO();
        usersDTO.setUsername("testuser");
        usersDTO.setEmail("test@example.com");
        usersDTO.setPassword("password");
    }

    @Test
    void addUser_success() throws Exception {
        when(service.addUser(any(UsersDTO.class))).thenReturn(usersDTO);

        mockMvc.perform(post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("User was successfully added"))
                .andExpect(jsonPath("$.status").value(true));
    }
    @Test
    void addUser_EmailFail() throws  Exception {
        usersDTO.setEmail("");
        when(service.addUser(any(UsersDTO.class))).thenReturn(usersDTO);
        mockMvc.perform(post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[Email cannot be empty]"))
                .andExpect(jsonPath("$.status").value(false));

        usersDTO.setEmail("hello");
        mockMvc.perform(post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[Email must be valid]"))
                .andExpect(jsonPath("$.status").value(false));
    }
    @Test
    void addUser_UsernameFail() throws Exception {
        usersDTO.setUsername(null);
        when(service.addUser(any(UsersDTO.class))).thenReturn(usersDTO);
        mockMvc.perform(post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[Username cannot be empty]"))
                .andExpect(jsonPath("$.status").value(false));
        usersDTO.setUsername("a");
        mockMvc.perform(post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[Username must be of 3 or more characters]"))
                .andExpect(jsonPath("$.status").value(false));

    }
    @Test
    void addUser_PasswordFail() throws  Exception{
        usersDTO.setPassword("");
        when(service.addUser(any(UsersDTO.class))).thenReturn(usersDTO);
        mockMvc.perform(post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[Password cannot be empty]"))
                .andExpect(jsonPath("$.status").value(false));
    }
}
