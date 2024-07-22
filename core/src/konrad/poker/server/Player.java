package konrad.poker.server;

public class Player {

    private int money;
    private Card card1;
    private Card card2;

    public Player(int money, Card card1, Card card2) {
        this.money = money;
        this.card1 = card1;
        this.card2 = card2;
    }

    public int getMoney() {
        return money;
    }

    public Card getCard1() {
        return card1;
    }

    public Card getCard2() {
        return card2;
    }


    @Override
    public String toString() {
        return "Player{" +
                "money=" + money +
                ", card1=" + card1 +
                ", card2=" + card2 +
                '}';
    }
}
