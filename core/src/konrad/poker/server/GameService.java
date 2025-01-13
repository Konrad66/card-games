package konrad.poker.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameService {

    private List<Card> cardDeck = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private GameRules gameRules;
    private int stake = 0;
    private int activePlayerIndex;

    public GameService(GameRules gameRules) {
        this.gameRules = gameRules;
        activePlayerIndex = gameRules.getFirstPlayerId();
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
        if (command.isMainCommand()) {
            return executeMainCommand(command);
        } else {
            return executeAdditionalCommand(command);
        }
    }

    private boolean executeMainCommand(Command command) {
        if (command.getPlayerId() != activePlayerIndex) {
            //prawdopodobnie blokuje nam te pierwsze bidy, może maja zle ustawione plauer id?
            System.out.println("blocked command: " + command);

            return false;
        }
        switch (command.getType()) {
            case RISE:
            case CALL:
                stake += command.getAmount();
                int playerBid = getPlayerById(command.getPlayerId()).placeBid(stake);
                command.setPlayerBid(playerBid);
                getDealer().receive(playerBid);
                break;
            case CHECK:
                break;
            case FOLD:
                break;
        }
        nextPlayer();
        return true;
    }

    private boolean executeAdditionalCommand(Command command) {
        switch (command.getType()) {
            case DRAW:
                getPlayerById(command.getPlayerId()).drawCard(cardDeck, command.getAmount());
                break;
            case SETUP_DONE:
                //todo nothing to do yet
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

    public Player getHuman() {
        return players.get(gameRules.getHumanId());
    }

    public Player getDealer() {
        return players.get(gameRules.getDealerId());
    }

    private Player getPlayerById(int id) {
        return players.get(id);
    }

    public void printStatus() {
        for (Player player : players) {
            System.out.println(player);
        }
        System.out.println("Nasz stake" + stake);
    }

    private void nextPlayer() {
        activePlayerIndex++;
        if (activePlayerIndex == getDealer().getId()) {
            activePlayerIndex++;
        }
        if (activePlayerIndex == players.size()) {
            activePlayerIndex = 0;
        }
    }
}