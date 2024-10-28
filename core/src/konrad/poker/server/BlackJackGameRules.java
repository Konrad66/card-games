package konrad.poker.server;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGameRules extends GameRules {

    private static final int NUMBER_OF_COMPUTERS = 4;

    public BlackJackGameRules() {
        for (int i = 1; players.size() < NUMBER_OF_COMPUTERS; i++) {
            if (i == HUMAN_ID) {
                continue;
            }
            players.add(new PlayerScheme(i, PlayerType.COMPUTER, false));
        }
        players.add(new PlayerScheme(HUMAN_ID, PlayerType.HUMAN, false));
        players.add(new PlayerScheme(DEALER_ID, PlayerType.DEALER, false));
    }

    @Override
    List<Command> getCommands() {
        List<Command> commandList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_COMPUTERS; i++) {
            commandList.add(new Command(CommandType.DRAW, 2, players.get(i).id()));
        }
        commandList.add(new Command(CommandType.DRAW, 2, HUMAN_ID));
        commandList.add(new Command(CommandType.DRAW, 1, DEALER_ID));
        return commandList;
    }
}