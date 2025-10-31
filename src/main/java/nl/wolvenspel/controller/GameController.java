package nl.wolvenspel.controller;

import nl.wolvenspel.model.GameState;
import nl.wolvenspel.model.Player;
import nl.wolvenspel.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    @Autowired  GameService gameService;

    @GetMapping("/game/start")
    public String startGame() {
        return gameService.startGame();
    }

    @PostMapping("/game/addPlayer")
    public String addPlayer(@RequestBody Player player) {
        return gameService.addPlayer(player);
    }

    @GetMapping("/game/state")
    public String getGameState() {
        return gameService.getGame().getState().toString();
    }

    @GetMapping("/game/players")
    public List<Player> getPlayers() {
        return gameService.getGame().getPlayers();
    }

}
