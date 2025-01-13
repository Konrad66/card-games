package konrad.poker.server;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGameRules extends GameRules {

    @Override
    List<Command> getStartCommandsUnsorted() {
        List<Command> commandList = new ArrayList<>();
        commandList.addAll(prepareDrawCommand(2));
        commandList.add(new Command(CommandType.DRAW, 1, getDealerId()));
        return commandList;
    }
}