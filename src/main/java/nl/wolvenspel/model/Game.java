package nl.wolvenspel.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import nl.wolvenspel.model.roles.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Component
public class Game {

    List<Player> players = new ArrayList<>();
    int round;
    GameState state;


    public void addPlayer(Player player) {
        if (state == GameState.LOBBY) {
            players.add(player);
        }
    }

    public void startGame() {
        if (players.size() >= 5) {
            assignRoles();
            state = GameState.NIGHT;
            round = 1;
        }
    }

    private void assignRoles() {
        List<Role> roles = generateRoles(players.size());
        Collections.shuffle(roles);
        int bound = players.size();
        for (int i = 0; i < bound; i++) {
            players.get(i).setRole(roles.get(i));
        }
    }

    private List<Role> generateRoles(int playerCount) {
        List<Role> roles = new ArrayList<>();
        roles.add(new Kringopziener());
        roles.add(new Herder());
        roles.add(new Wolf());
        roles.add(new Wolf());
        while (roles.size() < playerCount) {
            roles.add(new Schaap());
        }
        return roles;
    }

}
