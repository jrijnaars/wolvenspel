package nl.wolvenspel.model.roles;

import nl.wolvenspel.model.Team;
import nl.wolvenspel.model.roles.Role;
import org.springframework.stereotype.Component;

@Component
public class Herder extends Role {
    boolean hasProtectedTonight;
    boolean canSendAway;

    public Herder() {
        this.roleName = "Herder";
        this.team = Team.SCHAPEN;
        this.hasNightAction = true;
    }

    private String protectPlayer(String playerName) {
        // Logic for the night action of Herder
        return null;
    }
}
