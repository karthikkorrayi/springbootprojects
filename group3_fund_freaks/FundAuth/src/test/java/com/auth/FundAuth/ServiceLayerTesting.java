package com.auth.FundAuth;

import com.auth.FundAuth.Repository.UserRepository;
import com.auth.FundAuth.Services.UserService;
import com.auth.FundAuth.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceLayerTesting {
    UserRepository erepo= mock(UserRepository.class);

    @InjectMocks
    private UserService uservice;

    User u;
    @BeforeEach
    public void setUp()
    {
        u=new User(12,"Sudhir", "tuv@gmail.com", "abcd", "abcd");
    }

    @AfterEach
    public void tearDown()
    {
        u=null;
    }

    @Test
    @DisplayName("Check save success")
    public void givenUserToSaveReturnSaveUserSuccess()
    {
        when(erepo.findById(any())).thenReturn(Optional.ofNullable(null));
        when(erepo.save(any())).thenReturn(u);
        assertEquals(uservice.addUser(u),u.getId());

        //checking weather expectation is completing//
        verify(erepo,times(1)).save(any());
        verify(erepo,times(0)).findById(any());
    }

}
