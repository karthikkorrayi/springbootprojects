package com.auth.FundAuth.Exception;

public class PasswordNotCorrectException extends RuntimeException{
    public PasswordNotCorrectException(String message)
    {
        super(message);
    }
}
