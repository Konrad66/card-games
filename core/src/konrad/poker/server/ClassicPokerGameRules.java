package konrad.poker.server;

import java.util.ArrayList;
import java.util.List;

public class ClassicPokerGameRules extends GameRules {

    @Override
    List<Command> getStartCommandsUnsorted() {
        List<Command> commandList = new ArrayList<>();
        commandList.add(new Command(CommandType.RISE, 1, 1));
        commandList.add(new Command(CommandType.RISE, 2, 2));

        commandList.addAll(prepareDrawCommand(5));
        return commandList;
    }
}