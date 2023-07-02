package com.example.demo.Exceptions;

public class UserNotFound extends Exception{
    public UserNotFound(String message) {
        super(message);
    }
}
