package konrad.poker.server;

import java.util.ArrayList;
import java.util.List;

public class PokerHoldemGameRules extends GameRules {

    public PokerHoldemGameRules() {
        for (int i = 1; players.size() < NUMBER_OF_COMPUTERS; i++) {
            if (i == HUMAN_ID) {
                continue;
            }
            players.add(new PlayerScheme(i, PlayerType.COMPUTER, true));
        }
        players.add(new PlayerScheme(HUMAN_ID, PlayerType.HUMAN, false));
        players.add(new PlayerScheme(DEALER_ID, PlayerType.DEALER, false));
    }

    @Override
    List<Command> getStartCommandsUnsorted() {
        List<Command> commandList = new ArrayList<>();
        commandList.add(new Command(CommandType.RISE, 1, 1));
        commandList.add(new Command(CommandType.RISE, 1, 2));
        commandList.add(new Command(CommandType.CALL, 3));
        commandList.add(new Command(CommandType.CALL, 4));
        commandList.add(new Command(CommandType.CALL, 5));


        commandList.addAll(prepareDrawCommand(2));

        commandList.add(new Command(CommandType.DRAW, 3, DEALER_ID));
        return commandList;
    }
}