package konrad.poker.server;

import java.util.ArrayList;
import java.util.List;

public abstract class GameRules {

    List<PlayerScheme> players = new ArrayList<>();
    static final int DEALER_ID = 6;
    static final int HUMAN_ID = 3;

    abstract List<Command> getStartCommands();

    public int getIdBy(PlayerType type) {
        if (type.equals(PlayerType.DEALER)) {
            return DEALER_ID;
        } else if (type.equals(PlayerType.HUMAN)) {
            return HUMAN_ID;
        } else {
            return 1;
        }
    }

    public List<PlayerScheme> getPlayers() {
        return players;
    }
}