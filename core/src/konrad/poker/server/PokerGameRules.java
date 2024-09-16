package konrad.poker.server;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PokerGameRules {

    private List<PlayerScheme> players = new ArrayList<>();
    private static final int NUMBER_OF_COMPUTERS = 4;
    private static final int DEALER_ID = 6;
    private static final int HUMAN_ID = 3;

    public PokerGameRules() {
        for (int i = 1; players.size() < NUMBER_OF_COMPUTERS; i++) {
            if (i == HUMAN_ID) {
                continue;
            }
            players.add(new PlayerScheme(i, PlayerType.COMPUTER, true));
        }
        players.add(new PlayerScheme(HUMAN_ID, PlayerType.HUMAN, false));
        players.add(new PlayerScheme(DEALER_ID, PlayerType.DEALER, false));
    }

    public List<Command> getStartCommands() {
        List<Command> commandList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_COMPUTERS; i++) {
            commandList.add(new Command(CommandType.DRAW, 2, players.get(i).id()));
        }
        commandList.add(new Command(CommandType.DRAW, 2, HUMAN_ID));
        commandList.add(new Command(CommandType.DRAW, 3, DEALER_ID));
        commandList.sort(Comparator.comparingInt(Command::getPlayerId));
        //todo przeanalizowac
        //commandList.sort((command1, command2) -> command1.getPlayerId() - command2.getPlayerId());
        return commandList;
    }

    public List<PlayerScheme> getPlayers() {
        return players;
    }
}