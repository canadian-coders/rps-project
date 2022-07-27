package com.example.rpsproject.service;

import com.example.rpsproject.dao.GameRepository;
import com.example.rpsproject.dto.NewGameResDTO;
import com.example.rpsproject.model.Game;
import com.example.rpsproject.model.Move;
import com.example.rpsproject.model.Status;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    GameRepository gameRepository;

    public NewGameResDTO createNewGame(String playerName) throws IllegalArgumentException {
        String name = playerName.trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Please provide a playerName to continue");
        }

        Game newGame = new Game(name);
        Game resGame = gameRepository.save(newGame);
        return modelMapper.map(resGame,NewGameResDTO.class);
    }

    public int generateMoveInt() {
        Random number = new Random();
        int upper = 3;
        return number.nextInt(upper);
    }

    // function to generate computer move
    public Move generateComputerMove(int moveInt) {
        if (moveInt == 0) {
            return Move.Rock;
        } else if (moveInt == 1) {
            return Move.Paper;
        } else {
            return Move.Scissors;
        }
    }

    // update game -> get game by id, then get the computer move, get the result from Move enum and set the computerMove and result to the game
    public Game updateGame(int id, Move playerMove) {
        return new Game();
    }
}
