package nl.wolvenspel.controller;

import nl.wolvenspel.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired  GameService gameService;

    @GetMapping("/game/start")
    public String startGame() {
        gameService.startGame();
        return "Hallo van de backend!";
    }

}
