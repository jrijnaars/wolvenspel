package nl.wolvenspel.model.roles;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.wolvenspel.model.Team;
import org.springframework.stereotype.Component;

@Component
public class Role {

    @JsonProperty("roleName")
    String name;
    Team team;
    boolean hasNightAction;
}
