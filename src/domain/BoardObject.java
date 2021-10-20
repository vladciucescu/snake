package domain;

public enum BoardObject {
    EMPTY(" "),
    APPLE("."),
    SNAKE_HEAD("*"),
    SNAKE_SEGMENT("+");

    private String symbol;

    BoardObject(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
