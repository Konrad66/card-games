package konrad.poker.client;

public enum Color {
    CLUBS("clubs"), DIAMONDS("diamonds"), HEARTS("hearts"), SPADES("spades");

    private String name;

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
