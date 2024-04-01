package com.FundFreaks.FundStartup;

import com.FundFreaks.FundStartup.controller.startupController;
import com.FundFreaks.FundStartup.model.ideaModel;
import com.FundFreaks.FundStartup.model.startupModel;
import com.FundFreaks.FundStartup.service.startupService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class controllerTesting {
    startupService ur=mock(startupService.class);
    @InjectMocks
    private startupController ucon;
    @Autowired
    private MockMvc mockmvc;
    @Autowired
    public startupModel model;
    @Autowired
    ideaModel imod;
    List<ideaModel> idmod=new ArrayList<ideaModel>();
    @BeforeEach
    void setUp()
    {
        imod=new ideaModel(4,"hello","hello",3131,1234,true,"zolo");
        idmod.add(imod);
        model=new startupModel(1,"Zolo","Zolo@2023","Bangalore","PG",idmod);
        mockmvc= MockMvcBuilders.standaloneSetup(ucon).build();
    }
    @AfterEach
    void tearDown()
    {
        model=null;
    }
    @Test
    @DisplayName("Test save user in controller")
    void givenUserToSaveReturnSaveUser() throws Exception
    {
        when(ur.saveStartup(model)).thenReturn(model);
        mockmvc.perform(post("/startup/create").contentType(MediaType.APPLICATION_JSON).content(conString(model))).andExpect(status().isCreated());
    }
    public String conString(startupModel model) throws JsonProcessingException
    {
        ObjectMapper obj=new ObjectMapper();
        String jsoncontent=obj.writeValueAsString(model);
        return jsoncontent;
    }
}
