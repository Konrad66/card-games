package konrad.poker.server;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class GameRules {
//TODO metoda szablonowa - wzorzec projektowy - template method

    List<PlayerScheme> players = new ArrayList<>();
    static final int NUMBER_OF_COMPUTERS = 4;

    public GameRules() {
        for (int i = 0; i < NUMBER_OF_COMPUTERS+1; i++) {
            if (i == getHumanId()) {
                continue;
            }
            players.add(new PlayerScheme(i, PlayerType.COMPUTER, true));
        }
        players.add(new PlayerScheme(getHumanId(), PlayerType.HUMAN, false));
        players.add(new PlayerScheme(getDealerId(), PlayerType.DEALER, false));
    }

    final List<Command> getStartCommands() {
        List<Command> commandList = getStartCommandsUnsorted();
        commandList.sort(Comparator.comparingInt(Command::getPlayerId));
        commandList.sort((command1, command2) -> command1.getPlayerId() - command2.getPlayerId());
        commandList.add(new Command(CommandType.SETUP_DONE, -1));
        return commandList;
    }

    abstract List<Command> getStartCommandsUnsorted();

    List<Command> prepareDrawCommand(int amount) {
        List<Command> commandList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_COMPUTERS; i++) {
            commandList.add(new Command(CommandType.DRAW, amount, players.get(i).id()));
        }
        commandList.add(new Command(CommandType.DRAW, amount, getHumanId()));
        return commandList;
    }

    final int getIdBy(PlayerType type) {
        if (type.equals(PlayerType.DEALER)) {
            return getDealerId();
        } else if (type.equals(PlayerType.HUMAN)) {
            return getHumanId();
        } else {
            throw new IllegalStateException("no specific ID for computer type - many computers allowed");
        }
    }

    final List<PlayerScheme> getPlayers() {
        return players;
    }

    public int getFirstPlayerId() {
        return 0;
    }

    /**
     * AS LONG AS GAME IS STILL FOR 5 PLAYERS {@link GameRules#NUMBER_OF_COMPUTERS}
     * @return 2
     */

    public int getHumanId() {
         return 2;
    }

    /**
     * AS LONG AS GAME IS STILL FOR 5 PLAYERS {@link GameRules#NUMBER_OF_COMPUTERS}
     * @return 5
     */

    public int getDealerId() {
        return 5;
    }
}