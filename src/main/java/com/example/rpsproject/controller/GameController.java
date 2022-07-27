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
    public ResponseEntity<?> createNewGame(@PathVariable("playerName") String playerName) {
        try {
            // invokes createNewGame with the playerName
            NewGameResDTO resDTO = gameService.createNewGame(playerName);
            // create and return response entity with status 200 and bodyDTO
            return ResponseEntity.ok().body(resDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    //patch mapping
    @PatchMapping("/{gameId}")
    public ResponseEntity<?> resolveGame(@PathVariable("gameId") String gameId, @RequestBody String playerMove) {
        return ResponseEntity.ok().body("test");
    }
    //take in the game id (path var), playerMove (body)
    // parseInt gameId and change string to Move enum
    //query the game id first
    //generate a computer move
    //check result of computer vs player
    //add the player move, computer move, result, and set status to the game object and update game
    //response entity returns game object
}
