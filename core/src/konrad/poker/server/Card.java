package konrad.poker.server;

import konrad.poker.client.Color;
import konrad.poker.client.Rank;

public class Card {

    private Color color;
    private Rank rank;

    public Card(Color color, Rank rank) {
        this.color = color;
        this.rank = rank;
    }

    public Color getColor() {
        return color;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + color;
    }
}