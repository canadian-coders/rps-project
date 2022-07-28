package com.example.rpsproject.controller;

import com.example.rpsproject.dto.NewGameResDTO;
import com.example.rpsproject.exception.GameNotFound;
import com.example.rpsproject.model.Game;
import com.example.rpsproject.model.Move;
import com.example.rpsproject.model.Result;
import com.example.rpsproject.model.Status;
import com.example.rpsproject.service.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    @Test
    void createNewGame_positiveTest() {

        NewGameResDTO responseDto = new NewGameResDTO(0, Status.Started, "John Doe");

        when(gameService.createNewGame("John Doe")).thenReturn(responseDto);

        ResponseEntity<?> actualResponse = gameController.createNewGame("John Doe");

        Assertions.assertEquals(responseDto, actualResponse.getBody());
    }

    @Test
    void createNewGame_negativeTest() {
        when(gameService.createNewGame("test")).thenThrow(IllegalArgumentException.class);
        ResponseEntity<?> response = gameController.createNewGame("test");
        int expectedStatus = 400;
        assertThat(response.getStatusCodeValue()).isEqualTo(expectedStatus);
    }

    @Test
    void resolveGame_positiveTest() throws GameNotFound {
        Game game = new Game(0, "John Doe", Move.Paper, Move.Rock, Status.Finished, Result.Win);
        when(gameService.updateGame(0, Move.Paper)).thenReturn(game);

        ResponseEntity<?> actualResponse = gameController.resolveGame(0, "paper");
        Assertions.assertEquals(game, actualResponse.getBody());
    }

    @Test
    void resolveNewGame_invalidGameId() throws GameNotFound {
        when(gameService.updateGame(-1, Move.Paper)).thenThrow(IllegalArgumentException.class);
        ResponseEntity<?> response = gameController.resolveGame(-1, "paper");
        int expectedStatus = 400;
        assertThat(response.getStatusCodeValue()).isEqualTo(expectedStatus);
    }

    @Test
    void resolveNewGame_invalidMove() throws GameNotFound {
        when(gameService.updateGame(1, Move.valueOf("   "))).thenThrow(IllegalArgumentException.class);
        ResponseEntity<?> response = gameController.resolveGame(0, "  ");
        int expectedStatus = 400;
        assertThat(response.getStatusCodeValue()).isEqualTo(expectedStatus);
    }

}
