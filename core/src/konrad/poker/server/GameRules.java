package konrad.poker.server;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class GameRules {
//TODO metoda szablonowa - wzorzec projektowy - template method

    List<PlayerScheme> players = new ArrayList<>();
    static final int DEALER_ID = 6;
    static final int HUMAN_ID = 3;
    static final int NUMBER_OF_COMPUTERS = 4;

    final List<Command> getStartCommands() {
        List<Command> commandList = getStartCommandsUnsorted();
        commandList.sort(Comparator.comparingInt(Command::getPlayerId));
        commandList.sort((command1, command2) -> command1.getPlayerId() - command2.getPlayerId());
        return commandList;
    }

    abstract List<Command> getStartCommandsUnsorted();

    List<Command> prepareDrawCommand(int amount) {
        List<Command> commandList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_COMPUTERS; i++) {
            commandList.add(new Command(CommandType.DRAW, amount, players.get(i).id()));
        }
        commandList.add(new Command(CommandType.DRAW, amount, HUMAN_ID));
        return commandList;
    }

    final int getIdBy(PlayerType type) {
        if (type.equals(PlayerType.DEALER)) {
            return DEALER_ID;
        } else if (type.equals(PlayerType.HUMAN)) {
            return HUMAN_ID;
        } else {
            return 1;
        }
    }

    final List<PlayerScheme> getPlayers() {
        return players;
    }
}