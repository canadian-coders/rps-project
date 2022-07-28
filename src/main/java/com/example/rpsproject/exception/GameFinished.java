package com.example.rpsproject.exception;

public class GameFinished extends Exception{
    public GameFinished() {
        super();
    }

    public GameFinished(String message) {
        super(message);
    }
}
