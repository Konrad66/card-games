package konrad.poker.server;

import java.util.List;

public abstract class GameRules {

    abstract List<Command> getStartCommands();

    abstract int getIdBy(PlayerType type);

    abstract List<PlayerScheme> getPlayers();
}