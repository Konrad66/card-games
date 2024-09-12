package konrad.poker.server;

public class Dealer extends Player{

    public Dealer(int id, boolean hiddenCards) {
        super(Integer.MAX_VALUE, id, hiddenCards);
    }
}