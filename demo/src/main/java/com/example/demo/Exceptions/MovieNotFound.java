package com.example.demo.Exceptions;

public class MovieNotFound extends Exception{
    public MovieNotFound(String message) {
        super(message);
    }
}
