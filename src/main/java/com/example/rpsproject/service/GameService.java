package com.example.rpsproject.service;

import com.example.rpsproject.dao.GameRepository;
import com.example.rpsproject.dto.NewGameResDTO;
import com.example.rpsproject.exception.GameNotFound;
import com.example.rpsproject.model.Game;
import com.example.rpsproject.model.Move;
import com.example.rpsproject.model.Result;
import com.example.rpsproject.model.Status;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
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
        switch (moveInt) {
            case 0:
                return Move.Rock;
            case 1:
                return Move.Paper;
            case 2:
                return Move.Scissors;
            default:
                throw new IllegalArgumentException("Computer did not make a move");
        }
    }



    // update game -> get game by id, then get the computer move, get the result from Move enum and set the computerMove and result to the game
    @Transactional
    public Game updateGame(int id, Move playerMove) throws GameNotFound {
        //checks gameid exists
        Optional<Game> game = gameRepository.findById(id);
        if (!game.isPresent()) {
            throw new GameNotFound("Game with id:" + id + " is not found");
        }
        int moveInt = generateMoveInt();
        Move computerMove = generateComputerMove(moveInt);
        //check draw
        Result result = null;
        if (playerMove == computerMove) {
            result = Result.Draw;
        //check player wins or not
        } else {
            Boolean result2 = playerMove.winOrNot(computerMove);
            if (result2) {
                result = Result.Win;
            } else {
                result = Result.Lose;
            }
        }
        Game currentGame =game.get();
        currentGame.setPlayerMove(playerMove);
        currentGame.setComputerMove(computerMove);
        currentGame.setResult(result);
        currentGame.setStatus(Status.Finished);
        return gameRepository.save(currentGame);
    }
}
