package com.example.rpsproject.controller;

import com.example.rpsproject.dto.NewGameResDTO;
import com.example.rpsproject.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    // Post mapping
    @PostMapping("/{playerName}")
    // Receives post request with the playerName as a path variable
    public ResponseEntity<NewGameResDTO> createNewGame(@PathVariable("playerName") String playerName) {
        // invokes createNewGame with the playerName
        NewGameResDTO resDTO = gameService.createNewGame(playerName);
        // create and return response entity with status 200 and bodyDTO
        return ResponseEntity.ok().body(resDTO);
    }
}
