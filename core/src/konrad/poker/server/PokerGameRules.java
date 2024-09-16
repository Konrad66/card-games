package konrad.poker.server;

import java.util.ArrayList;
import java.util.List;

public class PokerGameRules {

    private List<PlayerScheme> players = new ArrayList<>();
    private static final int NUMBER_OF_COMPUTERS = 4;
    private static final int DEALER_ID = 6;
    private static final int HUMAN_ID = 1;

    public PokerGameRules() {
        //todo ma przechodzić od 1 - 5 pomijając numer gracza
        for (int i = 0; i < NUMBER_OF_COMPUTERS; i++) {
//            if (i == HUMAN_ID) {
//                continue;
//            }
            //todo uncomment
            players.add(new PlayerScheme(i + 2, PlayerType.COMPUTER, true));
        }
        players.add(new PlayerScheme(HUMAN_ID, PlayerType.HUMAN, false));
        players.add(new PlayerScheme(DEALER_ID, PlayerType.DEALER, false));
    }

    public List<Command> getStartCommands() {
        List<Command> commandList = new ArrayList<>();
        commandList.add(new Command(CommandType.DRAW, 2, HUMAN_ID));
        for (int i = 0; i < NUMBER_OF_COMPUTERS; i++) {
            commandList.add(new Command(CommandType.DRAW, 2, players.get(i).id()));
        }
        commandList.add(new Command(CommandType.DRAW, 3, DEALER_ID));
        return commandList;
    }

    public List<PlayerScheme> getPlayers() {
        return players;
    }
}