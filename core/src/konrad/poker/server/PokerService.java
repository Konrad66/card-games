package konrad.poker.server;

import konrad.poker.client.Color;
import konrad.poker.client.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PokerService {

    private List<Card> cardDeck = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private PokerGameRules pokerGameRules = new PokerGameRules();


    public PokerService() {
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
        List<PlayerScheme> playerSchemes = pokerGameRules.getPlayers();
        for (PlayerScheme playerScheme : playerSchemes) {
            Player player;
            //todo switch
            if (playerScheme.getPlayerType().equals(PlayerType.COMPUTER)) {
                player = new Player(1000, playerScheme.getId(), playerScheme.isHiddenCards());
            } else if (playerScheme.getPlayerType().equals(PlayerType.HUMAN)) {
                player = new Player(1000, playerScheme.getId(), playerScheme.isHiddenCards());
            } else {
                player = new Dealer(playerScheme.getId(), playerScheme.isHiddenCards());
            }
            players.add(player);
        }
    }

    public Card getCard() {
        return cardDeck.get(4);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Card getDeckCard(int i) {
        return cardDeck.get(i);
    }

    public boolean executeCommand(Command command) {
        switch (command.getType()) {
            case DRAW:
                players.get(0).drawCard(cardDeck, command.getAmount()); //todo dynamiczne wybieranie playera po id
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
}

//todo zastanowic sie jak widzimy karty innego gracza