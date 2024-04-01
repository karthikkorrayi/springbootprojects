package com.auth.FundAuth.Controller;

import com.auth.FundAuth.Exception.IdNotFoundException;
import com.auth.FundAuth.Exception.PasswordNotCorrectException;
import com.auth.FundAuth.Exception.UserAlreadyExistsException;
import com.auth.FundAuth.Services.UserService;
import com.auth.FundAuth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService service;

    @PostMapping("register")
    public ResponseEntity<?>registerUser(@RequestBody User u)
    {
        try {
           // service.addUser(u);
            return new ResponseEntity<>(service.addUser(u),HttpStatus.OK);
        }
        catch(UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("getAllUsers")
    public ResponseEntity<?>getAllUsers()
    {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?>userById(@PathVariable int id)
    {
        //return new ResponseEntity<>(service.getUserById(id),HttpStatus.OK);

        try {
            return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
        }
        catch(IdNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/login/{id}/{password}")
    public ResponseEntity<?> login(@PathVariable int id, @PathVariable String password)
    {
        //return new ResponseEntity(service.getToken(id, password), HttpStatus.OK);

        try {
            return new ResponseEntity(service.getToken(id, password), HttpStatus.OK);
        }
        catch(IdNotFoundException | PasswordNotCorrectException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping ("updateUser/{id}")
    public ResponseEntity<?>updateUserById(@PathVariable int id, @RequestBody User user)
    {

        try {
            return new ResponseEntity<>(service.updateUserById(id, user), HttpStatus.OK);
        }
        catch(IdNotFoundException  e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


    }
}
