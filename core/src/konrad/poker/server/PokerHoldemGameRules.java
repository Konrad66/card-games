package konrad.poker.server;

import java.util.ArrayList;
import java.util.List;

public class PokerHoldemGameRules extends GameRules {

    @Override
    List<Command> getStartCommandsUnsorted() {
        List<Command> commandList = new ArrayList<>();
        commandList.addAll(prepareDrawCommand(2));
        commandList.add(new Command(CommandType.RISE, 1, 0));
        commandList.add(new Command(CommandType.RISE, 1, 1));
//        commandList.add(new Command(CommandType.DRAW, 3, DEALER_ID));
        return commandList;
    }
}