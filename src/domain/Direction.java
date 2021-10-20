package domain;

public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0,-1),
    RIGHT(0, 1);

    private int rowIndex;
    private int columnIndex;

    Direction(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }
}
