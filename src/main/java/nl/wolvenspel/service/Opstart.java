package nl.wolvenspel.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.wolvenspel.model.Game;
import nl.wolvenspel.model.GameState;
import nl.wolvenspel.model.Player;
import nl.wolvenspel.model.roles.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
@Getter
public class Opstart {

    @Autowired
    private Game game;

    public String startGame() {

        String message = "";

        if (game.getPlayers() == null || game.getPlayers().isEmpty()) {
            String errorNoPlayers = "Geen spelers ingeschreven voor het spel.";
            log.info(errorNoPlayers);
            message = errorNoPlayers;
        } else if (game.getPlayers().size() < 4) {
            String errorToFewPlayers = "Niet genoeg spelers om het spel te starten. Minimaal 4 spelers vereist.";
            log.info(errorToFewPlayers);
            message = errorToFewPlayers;
        }

        if (game.getPlayers().size() >= 4 && game.getState() == GameState.LOBBY) {
            assignRoles();
            game.setState(GameState.OPSTART);
            game.setRound(1);
            log.info("spel gestart: " + game);
            message = "Spel gestart met " + game.getPlayers().size() + " spelers.";
        }

        return message;
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

    public String addPlayer(Player player) {
        String message;
        if (game.getState() != GameState.LOBBY) {
            message = "Spel is al begonnen. Nieuwe spelers kunnen niet worden toegevoegd.";
        } else if (game.getPlayers().stream().anyMatch(p -> p.getName().equals(player.getName()))) {
            message = "Speler met naam " + player.getName() + " is al toegevoegd.";
        } else {
            game.getPlayers().add(player);
            message = "Speler " + player.getName() + " succesvol toegevoegd.";
            log.info(message);
        }

        return message;
    }

}
