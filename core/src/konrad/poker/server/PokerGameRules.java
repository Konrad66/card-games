package konrad.poker.server;

import java.util.ArrayList;
import java.util.List;

public class PokerGameRules {

    private List<PlayerScheme> players = new ArrayList<>();
    private static final int NUMBER_OF_PLAYERS = 5;

    public PokerGameRules() {
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            players.add(new PlayerScheme(i + 1, PlayerType.HUMAN));
        }
        players.add(new PlayerScheme(6, PlayerType.DEALER));
    }

    public List<Command> getStartCommands() {
        List<Command> commandList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            commandList.add(new Command(CommandType.DRAW, 2, players.get(i).getId()));
        }
        commandList.add(new Command(CommandType.DRAW, 3, 6));
        return commandList;
    }
    //todo magic number dealer = 6, do zmiennej

    public List<PlayerScheme> getPlayers() {
        return players;
    }
}