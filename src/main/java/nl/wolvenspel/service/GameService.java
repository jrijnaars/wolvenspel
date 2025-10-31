package nl.wolvenspel.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.wolvenspel.model.Game;
import nl.wolvenspel.model.GameState;
import nl.wolvenspel.model.Player;
import nl.wolvenspel.model.roles.Wolf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Getter
public class GameService {


    @Autowired
    private Game game;

    @Autowired
    private Opstart opstart;

    public GameService(Game game) {
        this.game = game;
        this.game.setState(GameState.LOBBY);
    }


    public String startGame() {
        return opstart.startGame();
    }

    public String addPlayer(Player player) {
        return opstart.addPlayer(player);
    }

    public void nextPhase() {
        if (game.getState() == GameState.NACHT) {
            game.setState(GameState.DAG);
        } else if (game.getState() == GameState.DAG) {
            game.setRound(game.getRound() + 1);
            game.setState(GameState.NACHT);
        }
    }

    public boolean checkWinCondition() {
        boolean wolvenOver = game.getPlayers().stream()
                .anyMatch(p -> p.isAlive() && p.getRole() instanceof Wolf);
        boolean goedOver = game.getPlayers().stream()
                .anyMatch(p -> p.isAlive() && !(p.getRole() instanceof Wolf));

        if (!wolvenOver || !goedOver) {
            game.setState(GameState.EINDE);
            return true;
        }
        return false;
    }

}
