package konrad.poker.server;

public class Command {

    private CommandType type;
    private int amount;
    private int playerId;


    public Command(CommandType type, int amount, int playerId) {
        this.type = type;
        this.amount = amount;
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

    @Override
    public String toString() {
        return "Command{" +
                "type=" + type +
                ", amount=" + amount +
                ", playerId=" + playerId +
                '}';
    }
}