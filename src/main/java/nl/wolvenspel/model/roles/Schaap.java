package nl.wolvenspel.model.roles;

import nl.wolvenspel.model.Team;
import org.springframework.stereotype.Component;

@Component
public class Schaap extends Role{

    public Schaap() {
        this.name = "Schaap";
        this.team = Team.SCHAPEN;
        this.hasNightAction = false;
    }
}
