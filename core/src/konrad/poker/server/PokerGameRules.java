package konrad.poker.server;

import java.util.ArrayList;
import java.util.List;

public class PokerGameRules {

    public List<Command> getStartCommands() {
        List<Command> commandList = new ArrayList<>();
        commandList.add(new Command(CommandType.DRAW, 2));
        return commandList;
    }
}
