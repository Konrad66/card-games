package konrad.poker.server;

import java.util.ArrayList;
import java.util.Comparator;
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
    public List<Command> getStartCommands() {
        List<Command> commandList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_COMPUTERS; i++) {
            commandList.add(new Command(CommandType.DRAW, 2, players.get(i).id()));
        }
        commandList.add(new Command(CommandType.DRAW, 2, HUMAN_ID));
        commandList.add(new Command(CommandType.DRAW, 1, DEALER_ID));
        commandList.sort(Comparator.comparingInt(Command::getPlayerId));
        //todo przeanalizowac
        commandList.sort((command1, command2) -> command1.getPlayerId() - command2.getPlayerId());
        return commandList;
    }
}