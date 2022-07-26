package com.example.rpsproject.service;

import com.example.rpsproject.dao.GameRepository;
import com.example.rpsproject.dto.NewGameResDTO;
import com.example.rpsproject.model.Game;
import com.example.rpsproject.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {
    @Spy
    private ModelMapper modelMapper;

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @Test
    void verify_new_game_created_PositiveTest() {
        // create DTO for GameService's createNewGame method to return
        NewGameResDTO responseDto = new NewGameResDTO(0, Status.started, "John Doe");

        // mock game object to return from the DAO layer
        Game game = new Game(0,"John Doe",null, null,Status.started, null);
        // mock method call to return the mock game object
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        // map game object to NewGameResDTO
        when(modelMapper.map(game, NewGameResDTO.class)).thenReturn(responseDto);

        // Call createNewGame with the player name and store it in actualDTO
        NewGameResDTO actualDto = gameService.createNewGame("John Doe");

        Assertions.assertEquals(responseDto, actualDto);
    }

}
