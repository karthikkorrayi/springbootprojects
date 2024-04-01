package com.auth.FundAuth;
//package com.Bank.UserManagementService;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.auth.FundAuth.Controller.AuthController;
import com.auth.FundAuth.Services.UserService;
import com.auth.FundAuth.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
//import com.Bank.UserManagementService.Controller.UserController;
//import com.Bank.UserManagementService.Model.User;
//import com.Bank.UserManagementService.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class ControllerLayerTesting {
    UserService userservice = mock(UserService.class);

    @InjectMocks
    private AuthController ucon;

    @Autowired
    private MockMvc mockmvc;

    private User user;
    List<User> userL;


    @BeforeEach
    void setUp()
    {
        user=new User(77, "vijay", "abc12@mail.com", "hi$67", "hi$67");
        mockmvc=MockMvcBuilders.standaloneSetup(ucon).build();
        userL=new ArrayList<User>();
        userL.add(user);
    }

    @AfterEach
    void tearDown() {
        user=null;
    }

    @Test
    @DisplayName("Test to register user")
    void givenUserSavedReturnSameUser() throws Exception
    {
        when(userservice.addUser(user)).thenReturn("");
        mockmvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(user))).andExpect(status().isOk());
    }


    public String jsonToString(User u) throws JsonProcessingException
    {
        ObjectMapper obj=new ObjectMapper();
        String jsonContent=obj.writeValueAsString(u);
        return jsonContent;

    }

    @Test
    @DisplayName("Test get all Users")
    void shouldFetchAllUsers() throws Exception
    {
        when(userservice.getAllUsers()).thenReturn(userL);
        this.mockmvc.perform(get("/auth/getAllUsers"))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("Test get user by id")
    void shouldFetchUserById() throws Exception {
        when(userservice.getUserById(user.getId())).thenReturn(user);
        this.mockmvc.perform(get("/auth/user/77"))
                .andExpect(status().isOk());

    }

//    @Test
//    @DisplayName("Test update user")
//    void shouldUpdateUser() throws Exception
//    {
//        when(userservice.addUser(user)).thenReturn("");
//        mockmvc.perform(post("/api/auth/updateUser/77")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(user)))
//                .andExpect(status().isOk());
//    }
}

