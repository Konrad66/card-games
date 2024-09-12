package konrad.poker.server;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> playerCards = new ArrayList<>();
    private int money;
    private int id;
    private boolean hiddenCards;

    public Player(int money, int id, boolean hiddenCards) {
        this.money = money;
        this.id = id;
        this.hiddenCards = hiddenCards;
    }

    public int getMoney() {
        return money;
    }

    public int getId() {
        return id;
    }

    public boolean isHiddenCards() {
        return hiddenCards;
    }

    public void drawCard(List<Card> cardsDeck, int amount) {
        for (int i =0; i < amount; i++){
            Card card = cardsDeck.get(0);
            playerCards.add(card);
            cardsDeck.remove(0);
        }
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerCards=" + playerCards +
                ", money=" + money +
                ", id=" + id +
                ", hiddenCards=" + hiddenCards +
                '}';
    }
}