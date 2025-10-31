package nl.wolvenspel.model.roles;

import nl.wolvenspel.model.Team;
import org.springframework.stereotype.Component;

@Component
public class Role {
    String roleName;
    Team team;
    boolean hasNightAction;
}
