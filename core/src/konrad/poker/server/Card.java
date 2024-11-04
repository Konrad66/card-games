package konrad.poker.server;

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