package domain;

import config.Defaults;
import config.Settings;

public class Board {
    private final BoardObject[][] board;
    private final int rows;
    private final int columns;

    public Board() {
        rows = Settings.getInstance().getRows().orElse(Defaults.DEFAULT_ROW_COUNT);
        columns = Settings.getInstance().getColumns().orElse(Defaults.DEFAULT_COLUMN_COUNT);
        board = createBoard(rows, columns);
    }

    private BoardObject[][] createBoard(int numberOfRows, int numberOfColumns) {
        var board = new BoardObject[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                board[i][j] = BoardObject.EMPTY;
            }
        }
        return board;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setBoardObject(Coordinates coordinates, BoardObject boardObject) {
        board[coordinates.row()][coordinates.column()] = boardObject;
    }

    public BoardObject getBoardObject(Coordinates coordinates) {
        return board[coordinates.row()][coordinates.column()];
    }
}
