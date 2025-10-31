package nl.wolvenspel.model.roles;

import nl.wolvenspel.model.Team;
import org.springframework.stereotype.Component;

@Component
public class Herder extends Role {
    boolean hasProtectedTonight;
    boolean canSendAway;

    public Herder() {
        this.name = "Herder";
        this.team = Team.SCHAPEN;
        this.hasNightAction = true;
    }

    private String protectPlayer(String playerName) {
        // Logic for the night action of Herder
        return null;
    }
}
