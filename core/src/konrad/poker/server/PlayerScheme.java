package konrad.poker.server;

public record PlayerScheme(
        int id,
        PlayerType playerType,
        boolean hiddenCards
) {
}