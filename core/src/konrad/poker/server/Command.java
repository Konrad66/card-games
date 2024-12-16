package konrad.poker.server;

public class Command {

    private CommandType type;
    private int amount;
    private int playerId;
    private int playerBid;

//todo różne sposoby budowania komend
    public Command(CommandType type, int amount, int playerId) {
        if (amount < 0) {
            amount = 0;
        }
        this.type = type;
        this.amount = amount;
        this.playerId = playerId;
    }

    public Command(CommandType type, int playerId){
        this.type = type;
        this.playerId = playerId;
    }

    public CommandType getType() {
        return type;
    }

    public int getAmount(){
        return amount;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getPlayerBid() {
        return playerBid;
    }

    public void setPlayerBid(int playerBid) {
        this.playerBid = playerBid;
    }

    @Override
    public String toString() {
        return "Command{" +
                "type=" + type +
                ", amount=" + amount +
                ", playerId=" + playerId +
                '}';
    }
}

//interfejs tworzenia komend dla humana
//interfejs tworzenia komend dla systemu
//interfejs tworzenia komend na start