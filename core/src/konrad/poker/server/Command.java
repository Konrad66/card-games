package konrad.poker.server;

public class Command {

    private CommandType type;
    private int amount;

    public Command(CommandType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public CommandType getType() {
        return type;
    }

    public int getAmount(){
        return amount;
    }


}