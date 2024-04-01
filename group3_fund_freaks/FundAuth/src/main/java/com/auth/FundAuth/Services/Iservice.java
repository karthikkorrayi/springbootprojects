package com.auth.FundAuth.Services;

import com.auth.FundAuth.model.User;

import java.util.List;
import java.util.Map;

public interface Iservice {
    public String addUser(User u);
    public List<User>getAllUsers();
    public User getUserById(int id);
    public Map<String,String>getToken(int id,String password);
    public User updateUserById(int id,User user);

    public String addStartup(User u);
    public String addInvestor(User u);

}
