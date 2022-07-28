package com.example.rpsproject.exception;

public class GameNotFound extends  Exception{


    public GameNotFound() {
        super();
    }

    public GameNotFound(String message) {
        super(message);
    }
}
