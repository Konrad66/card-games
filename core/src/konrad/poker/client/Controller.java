package konrad.poker.client;

import konrad.poker.server.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private PokerService pokerService = new PokerService();
    private PokerGameRules pokerGameRules = new PokerGameRules();
    private PokerGame pokerGame;
    private DeckGroup deck;
    private PlayerGroup player;

    public Controller(PokerGame pokerGame) {
        this.pokerGame = pokerGame;
    }

    void setupActors() {
        createPlayer();
        createDeck();
    }

    void createPlayer() {
        Player player = pokerService.getPlayer();
        MoneyActor moneyActor = new MoneyActor(player, pokerGame.getFont(), false);
        HandGroup hand = new HandGroup(NewCardDirection.LEFT);
        List<Card> cards = player.getPlayerCards();
        List<CardActor> cardActors = new ArrayList<>();
        for (Card card : cards) {
            cardActors.add(new CardActor(card));
        }
        hand.addActors(cardActors);
        this.player = new PlayerGroup(hand, moneyActor);
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
                deck.playerDraws(player, command.getAmount());
        }
    }


    public List<Card> getDeckCards() {
        return pokerService.getDeckCards();
    }

    public DeckGroup getDeck() {
        return deck;
    }

    public PlayerGroup getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "Controller{" +
                "player=" + player +
                ", deck=" + deck +
                '}';
    }
}
