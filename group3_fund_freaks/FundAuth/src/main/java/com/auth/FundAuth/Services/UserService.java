package com.auth.FundAuth.Services;

import com.auth.FundAuth.Exception.IdNotFoundException;
import com.auth.FundAuth.Exception.PasswordNotCorrectException;
import com.auth.FundAuth.Exception.UserAlreadyExistsException;
import com.auth.FundAuth.RabbitMqConfig.authDto;
import com.auth.FundAuth.Repository.UserRepository;
import com.auth.FundAuth.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements Iservice{

    @Autowired
    RabbitTemplate rt;
    @Autowired
    DirectExchange exchange;

    @Autowired
    private UserRepository urepo;


    @Override
    public String addStartup(User u) {
        JSONObject temp=new JSONObject();
        authDto dto=new authDto();
        temp.put("slog",u);
        dto.setJsonObject(temp);
        rt.convertAndSend(exchange.getName(),"startup_auth_route",dto);
        urepo.save(u);
        String str = "Successfully registered %s with name %s".formatted(u.getType(), u.getUname());
        return str;
    }



    @Override
    public String addInvestor(User u) {
        JSONObject temp=new JSONObject();
        authDto dto=new authDto();
        temp.put("ilog",u);
        dto.setJsonObject(temp);
        rt.convertAndSend(exchange.getName(),"investor_auth_route",dto);
        urepo.save(u);
        String str = "Successfully registered %s with name %s".formatted(u.getType(), u.getUname());
        return str;
    }

    @Override
    public String addUser(User u) {
        if(urepo.findById(u.getId())!= null)
            throw new UserAlreadyExistsException("User with this Id already Exists");


       urepo.save(u);

        if(u.getType().equals("startup"))
        {
            return addStartup(u);
        } else if (u.getType().equals("investor")) {
            return addInvestor(u);
        }
        return "please enter a valid user type";

    }

    @Override
    public List<User> getAllUsers() {
        return urepo.findAll();
    }

    @Override
    public User getUserById(int id) {
        if(urepo.findById(id)==null)
            throw new IdNotFoundException("User with the ID: "+ id+" not found");
        return urepo.findById(id);
    }

    @Override
    public Map<String, String> getToken(int id, String password) {

        if(urepo.findById(id)== null)
            throw new IdNotFoundException("User with the ID: "+ id +" not found");
        else if(!((urepo.findById(id).getPassword()).equals(password)))
            throw new PasswordNotCorrectException("password is not correct");
        String jwtToken= Jwts.builder().setSubject(urepo.findById(id).getUname())
                .setIssuedAt(new Date(0))
                .signWith(SignatureAlgorithm.HS256, "itckey")
                .compact();

        Map<String, String> jToken= new HashMap<String ,String>();
        jToken.put("token", jwtToken);

        return jToken;
    }

    @Override
    public User updateUserById(int id, User user) {


        User u= urepo.findById(id);
        if(u==null)
            throw new IdNotFoundException("User with the ID: "+ id +" not found");


        u.setUname(user.getUname());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setType(user.getType());


        return urepo.save(u);
    }



    }

