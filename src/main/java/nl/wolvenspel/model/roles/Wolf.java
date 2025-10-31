package nl.wolvenspel.model.roles;

import nl.wolvenspel.model.Team;
import org.springframework.stereotype.Component;

@Component
public class Wolf extends Role {

    public Wolf() {
        this.name = "Wolf";
        this.team = Team.WOLVEN;
        this.hasNightAction = true;
    }

    private String chooseVictim(String playerName) {
        // Logic for the night action of Wolf
        return null;
    }
}
