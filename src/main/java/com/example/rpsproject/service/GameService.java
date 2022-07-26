package com.example.rpsproject.service;

import com.example.rpsproject.dao.GameRepository;
import com.example.rpsproject.dto.NewGameResDTO;
import com.example.rpsproject.model.Game;
import com.example.rpsproject.model.Status;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    GameRepository gameRepository;

    public NewGameResDTO createNewGame(String playerName) {
        Game newGame = new Game(playerName);
        Game resGame = gameRepository.save(newGame);
        return modelMapper.map(resGame,NewGameResDTO.class);
    };
}
