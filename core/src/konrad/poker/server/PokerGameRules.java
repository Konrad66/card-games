package konrad.poker.server;

import java.util.ArrayList;
import java.util.List;

public class PokerGameRules {

    public List<Command> getStartCommands() {
        List<Command> commandList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            commandList.add(new Command(CommandType.DRAW, 2));
        }
        return commandList;
    }
}