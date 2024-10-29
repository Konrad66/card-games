package konrad.poker.server;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGameRules extends GameRules {

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
    List<Command> getStartCommandsUnsorted() {
        List<Command> commandList = new ArrayList<>();
        commandList.addAll(prepareDrawCommand(2));
        commandList.add(new Command(CommandType.DRAW, 1, DEALER_ID));
        return commandList;
    }
}