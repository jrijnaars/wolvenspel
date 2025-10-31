package nl.wolvenspel.model.roles;

import nl.wolvenspel.model.Team;
import nl.wolvenspel.model.roles.Role;
import org.springframework.stereotype.Component;

@Component
public class Kringopziener extends Role {

    public Kringopziener() {
        this.roleName = "Kringopziener";
        this.team = Team.SCHAPEN;
        this.hasNightAction = true;
    }

    private String viewPlayerCard(String playerName) {
        // Logic for the night action of Kringopziener
        return null;
    }

}
