package com.itc.driver.exception;

public class DuplicateDriverIdException extends RuntimeException{
    public DuplicateDriverIdException(String message){
        super(message);
    }
}
