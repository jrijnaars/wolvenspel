package nl.wolvenspel.service;

import lombok.extern.slf4j.Slf4j;
import nl.wolvenspel.model.Game;
import nl.wolvenspel.model.GameState;
import nl.wolvenspel.model.roles.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class GameService {


    @Autowired
    private Game game;

    public GameService(Game game) {
        this.game = game;
    }

    public void startGame() {
        if (game.getPlayers().size() >= 4 && game.getState() == GameState.LOBBY) {
            assignRoles();
            game.setState(GameState.NIGHT);
            game.setRound(1);
        }
    }

    private void assignRoles() {
        List<Role> roles = generateRoles(game.getPlayers().size());
        Collections.shuffle(roles);
        for (int i = 0; i < game.getPlayers().size(); i++) {
            game.getPlayers().get(i).setRole(roles.get(i));
        }
    }

    private List<Role> generateRoles(int playerCount) {
        List<Role> roles = new ArrayList<>();
        roles.add(new Kringopziener());
        roles.add(new Herder());
        roles.add(new Wolf());
        while (roles.size() < playerCount) {
            roles.add(new Schaap());
        }
        return roles;
    }

    public void nextPhase() {
        if (game.getState() == GameState.NIGHT) {
            game.setState(GameState.DAY);
        } else if (game.getState() == GameState.DAY) {
            game.setRound(game.getRound() + 1);
            game.setState(GameState.NIGHT);
        }
    }

    public boolean checkWinCondition() {
        boolean wolvenOver = game.getPlayers().stream()
                .anyMatch(p -> p.isAlive() && p.getRole() instanceof Wolf);
        boolean goedOver = game.getPlayers().stream()
                .anyMatch(p -> p.isAlive() && !(p.getRole() instanceof Wolf));

        if (!wolvenOver || !goedOver) {
            game.setState(GameState.ENDED);
            return true;
        }
        return false;
    }

}
