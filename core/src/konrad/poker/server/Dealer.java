package konrad.poker.server;

public class Dealer extends Player {

    public Dealer(int id, boolean hiddenCards) {
        super(0, id, hiddenCards);
    }

    @Override
    public void placeBid(int bid) {
        money += bid;
    }
}