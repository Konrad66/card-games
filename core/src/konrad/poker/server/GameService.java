package konrad.poker.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameService {

    private List<Card> cardDeck = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private GameRules gameRules;
    private int stake = 0;

    public GameService(GameRules gameRules) {
        this.gameRules = gameRules;
        createCards();
        shuffleDeck();
        createPlayers();
    }

    private void createCards() {
        for (Color color : Color.values()) {
            for (Rank rank : Rank.values()) {
                cardDeck.add(new Card(color, rank));
            }
        }
    }

    private void shuffleDeck() {
        Collections.shuffle(cardDeck);
    }

    private void createPlayers() {
        List<PlayerScheme> playerSchemes = gameRules.getPlayers();
        for (PlayerScheme playerScheme : playerSchemes) {
            Player player;
            switch (playerScheme.playerType()) {
                case COMPUTER:
                case HUMAN:
                    player = new Player(1000, playerScheme.id(), playerScheme.hiddenCards());
                    break;
                case DEALER:
                    player = new Dealer(playerScheme.id(), playerScheme.hiddenCards());
                    break;
                default:
                    throw new IllegalStateException("Nieobsługiwany typ gracza");
            }
            players.add(player);
        }
    }


    public List<Command> getStartCommands() {
        return gameRules.getStartCommands();
    }

    public int getIdBy(PlayerType type) {
        return gameRules.getIdBy(type);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean executeCommand(Command command) {
        switch (command.getType()) {
            case DRAW:
                getPlayerById(command.getPlayerId()).drawCard(cardDeck, command.getAmount());
                break;
            case RISE:
            case CALL:
                stake += command.getAmount();
                int playerBid = getPlayerById(command.getPlayerId()).placeBid(stake);
                command.setPlayerBid(playerBid);
                getPlayerById(GameRules.DEALER_ID).receive(playerBid);
                break;
            case CHECK:
                break;
            case FOLD:
                break;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PokerService{" +
                "cardDeck=" + cardDeck +
                '}';
    }

    public List<Card> getDeckCards() {
        return cardDeck;
    }

    private Player getPlayerById(int id) {
        return players.stream()
                .filter(player -> player.getId() == id)
                .findAny()
                .orElseThrow();
    }

    public void printStatus() {
        for (Player player : players) {
            System.out.println(player);
        }
        System.out.println("Nasz stake" + stake);
    }
}