package konrad.poker.server;

import konrad.poker.client.Color;
import konrad.poker.client.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PokerService {

    private List<Card> cardDeck = new ArrayList<>();
    private Player player = new Player(1000);

    public PokerService() {
        createCards();
        shuffleDeck();
    }

    private void createCards() {
        for (Color color : Color.values()) {
            for (Rank rank : Rank.values()) {
                cardDeck.add(new Card(color, rank));
            }
        }
    }

    private void shuffleDeck(){
        Collections.shuffle(cardDeck);
    }

    public Card getCard() {
        return cardDeck.get(4);
    }

    public Player getPlayer(){
        return player;
    }


    public Card getDeckCard(int i) {
        return cardDeck.get(i);
    }

    public boolean executeCommand(Command command){
        switch (command.getType()){
            case DRAW:
                player.drawCard(cardDeck, command.getAmount());
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