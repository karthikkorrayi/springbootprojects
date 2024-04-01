package com.auth.FundAuth;

import com.auth.FundAuth.Repository.UserRepository;
import com.auth.FundAuth.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryLayerTesting {

    @Autowired
    private UserRepository repo;

    private User u;
    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp()
    {

        u=new User(123, "Rahul", "rahul@itc.com", "12345", "12345");
    }

    @AfterEach()
    void tearDown() {
        u=null;
        repo.deleteAll();

    }

    @Test
    @DisplayName("Test case for saving User object")
    public void givenUserToSaveReturnUer()
    {
        repo.save(u);
        User u1;

        u1=repo.findById(u.getId());

        assertNotNull(u1);
        assertEquals(u1.getId(),u.getId());

    }

    @Test
    @DisplayName("test case to fetch all the users")
    void retrieveAllTheUsers()
    {
        repo.save(u);
        List<User> users=repo.findAll();
        assertNotNull(users.size());
    }

    @Test
    @DisplayName("test case to find user by Id")
    public void testFindById()
    {

        User user = new User();
        user.setId(1);
        user.setUname("testUser");
        entityManager.persistAndFlush(user);
        // Perform the findById query
        User foundUser = repo.findById(1);
        // Assert the user is found
        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUname());
    }


    @Test
    @DisplayName("Test update user")
    void testupdateuser()
    {
        User user= new User(123, "Rahul", "rahul@itc.com", "12345", "12345");

        user.setUname("user");
        user.setEmail("email");
        user.setPassword("password");


        entityManager.persistAndFlush(user);

        user= repo.findById(123);
        assertEquals("user", user.getUname());
        assertEquals("email", user.getEmail());
        assertEquals("password", user.getPassword());

    }
}
