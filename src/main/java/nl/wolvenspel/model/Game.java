package nl.wolvenspel.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Game {

    List<Player> players = new ArrayList<>();
    int round;
    GameState state;

}
