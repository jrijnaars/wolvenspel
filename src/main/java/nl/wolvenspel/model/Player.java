package nl.wolvenspel.model;

import lombok.Getter;
import lombok.Setter;
import nl.wolvenspel.model.roles.Role;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Player {

    String name;
    Role role;
    boolean isAlive;
    boolean nominatedForElimination;


}
