package konrad.poker.server;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> playerCards = new ArrayList<>();
    private int money;
    private int id;

    public Player(int money, int id) {
        this.money = money;
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public int getId() {
        return id;
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
                '}';
    }
}
