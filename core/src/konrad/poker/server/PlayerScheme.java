package konrad.poker.server;

public class PlayerScheme {

    private int id;
    private PlayerType playerType;

    public PlayerScheme(int id, PlayerType playerType) {
        this.id = id;
        this.playerType = playerType;
    }

    public int getId() {
        return id;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}