package konrad.poker.server;

public enum CommandType {
    SETUP_DONE(false), DRAW(false), RISE(true), CALL(true), CHECK(true), FOLD(true);

    private boolean mainCommand;

    CommandType(boolean mainCommand) {
        this.mainCommand = mainCommand;
    }

    public boolean isMainCommand() {
        return mainCommand;
    }
}