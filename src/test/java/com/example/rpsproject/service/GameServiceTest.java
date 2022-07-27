package com.example.rpsproject.service;

import com.example.rpsproject.dao.GameRepository;
import com.example.rpsproject.dto.NewGameResDTO;
import com.example.rpsproject.model.Game;
import com.example.rpsproject.model.Move;
import com.example.rpsproject.model.Result;
import com.example.rpsproject.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {
    @Spy
    private ModelMapper modelMapper;

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @Test
    void verify_new_game_created_PositiveTest() {
        // create DTO for GameService's createNewGame method to return
        NewGameResDTO responseDto = new NewGameResDTO(0, Status.Started, "John Doe");

        // mock game object to return from the DAO layer
        Game game = new Game(0,"John Doe",null, null,Status.Started, null);
        // mock method call to return the mock game object
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        // map game object to NewGameResDTO
        when(modelMapper.map(game, NewGameResDTO.class)).thenReturn(responseDto);

        // Call createNewGame with the player name and store it in actualDTO
        NewGameResDTO actualDto = gameService.createNewGame("John Doe");

        Assertions.assertEquals(responseDto, actualDto);
    }


    @Test
    void invalid_request_with_blank_player_name_IllegalArgument() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gameService.createNewGame("           ");
        });
        String expectedMessage = "Please provide a playerName to continue";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_generateMoveInt() {
        int actual = gameService.generateMoveInt();

        assertThat(actual).isBetween(0, 2);
    }

    @Test
    void generateComputerMove_positiveTest() {
        Move computerMove1 = gameService.generateComputerMove(0);
        Move computerMove2 = gameService.generateComputerMove(1);
        Move computerMove3 = gameService.generateComputerMove(2);
        Assertions.assertEquals(Move.Rock, computerMove1);
        Assertions.assertEquals(Move.Paper, computerMove2);
        Assertions.assertEquals(Move.Scissors, computerMove3);
    }

    @Test
    void generateComputerMove_negativeTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Move computerMove = gameService.generateComputerMove(4);
        });
    }

    @Test
    void updateGame_positiveTest() {
        Game game = new Game(0, "John Doe", Move.Rock, Move.Paper, Status.Finished, Result.Lose);
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        Game actual = gameService.updateGame(0, Move.Rock);
        Assertions.assertEquals(game, actual);
    }

    @Test
    void updateGame_invalidGameId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Game game = gameService.updateGame(-1, Move.Rock);
        });
    }
}
