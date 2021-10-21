package domain;

public enum BoardObject {
    EMPTY(" "),
    APPLE("o"),
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
