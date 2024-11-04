package konrad.poker.server;

public enum Rank {

    TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"), FIVE(5, "5"), SIX(6, "6"), SEVEN(7, "7"), EIGHT(8, "8"), NINE(9, "9"), TEN(10, "10"), JACK(11, "J"), QUEEN(12, "Q"), KING(13, "K"), ACE(14, "A");

    private int power;

    private String symbol;

   Rank(int power, String symbol) {
        this.power = power;
        this.symbol = symbol;
    }

    public int getPower() {
        return power;
    }

    public String getSymbol() {
        return symbol;
    }
}