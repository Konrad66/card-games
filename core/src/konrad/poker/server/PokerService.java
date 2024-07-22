package konrad.poker.server;

import konrad.poker.client.Color;
import konrad.poker.client.Rank;

import java.util.ArrayList;
import java.util.List;

public class PokerService {

    private List<Card> cardDeck = new ArrayList<>();

    public PokerService() {
        createCards();
    }

    private void createCards() {
        for (Color color : Color.values()) {
            for (Rank rank : Rank.values()) {
                cardDeck.add(new Card(color, rank));
            }
        }
    }

    public Card getCard() {
        return cardDeck.get(4);
    }

    public Player getPlayer(){
        Card card = cardDeck.get(6);
        card.setHidden(false);
        return new Player(1000, card, cardDeck.get(50));
    }


}