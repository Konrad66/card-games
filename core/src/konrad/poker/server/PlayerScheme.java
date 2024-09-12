package konrad.poker.server;

public class PlayerScheme {

    private int id;
    private PlayerType playerType;
    private boolean hiddenCards;

    public PlayerScheme(int id, PlayerType playerType, boolean hiddenCards) {
        this.id = id;
        this.playerType = playerType;
        this.hiddenCards = hiddenCards;
    }

    public int getId() {
        return id;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public boolean isHiddenCards() {
        return hiddenCards;
    }
}