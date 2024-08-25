package konrad.poker.server;

import java.util.ArrayList;
import java.util.List;

public class PokerGameRules {

    private List<Integer> players = new ArrayList<>();
    private static final int NUMBER_OF_PLAYERS = 5;

    public PokerGameRules() {
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            players.add(i + 1);
        }
    }

    public List<Command> getStartCommands() {
        List<Command> commandList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            commandList.add(new Command(CommandType.DRAW, 2, players.get(i)));
        }
        return commandList;
    }

    public List<Integer> getPlayers() {
        return players;
    }
}