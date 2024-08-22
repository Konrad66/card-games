package konrad.poker.client;

import konrad.poker.server.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    private PokerService pokerService = new PokerService();
    private PokerGameRules pokerGameRules = new PokerGameRules();
    private PokerGame pokerGame;
    private DeckGroup deck;
    private Map<Integer,PlayerGroup> players = new HashMap<>();

    public Controller(PokerGame pokerGame) {
        this.pokerGame = pokerGame;
    }

    void setupActors() {
        createPlayer();
        createDeck();
    }

    void createPlayer() {
        List<Player> playersList = pokerService.getPlayers();
        PokerGameLayout layout = new PokerGameLayout();
        for (Player player : playersList) {
            PokerGameLayout.PlayerLayout playerLayout = layout.getLayoutFor(player.getId());
            MoneyActor moneyActor = null;
            if (playerLayout.isWithMoney()) {
                moneyActor = new MoneyActor(player, pokerGame.getFont(), playerLayout.isMovable(),playerLayout.getMoneyDirection());
            }
            HandGroup hand = new HandGroup(playerLayout.getHandDirection());
            PlayerGroup playerGroup = new PlayerGroup(hand, moneyActor);
            playerGroup.setX(playerLayout.getX());
            playerGroup.setY(playerLayout.getY());
            players.put(player.getId(), playerGroup);

        }
    }

    void createDeck() {
        deck = new DeckGroup(this);
    }


    void startGame() {
        List<Command> commands = pokerGameRules.getStartCommands();
        for (Command command : commands) {
            playCommand(command);
        }
    }

    private void playCommand(Command command) {
        boolean success = pokerService.executeCommand(command);
        if (success) {
            executeCommand(command);
        }
    }

    private void executeCommand(Command command) {
        switch (command.getType()) {
            case DRAW:
                deck.playerDraws(players.get(command.getPlayerId()), command.getAmount());
        }
    }


    public List<Card> getDeckCards() {
        return pokerService.getDeckCards();
    }

    public DeckGroup getDeck() {
        return deck;
    }

    public Map<Integer, PlayerGroup> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "Controller{" +
                "player=" + players +
                ", deck=" + deck +
                '}';
    }
}
