package com.auth.FundAuth.Exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message)
    {
        super(message);
    }
}
