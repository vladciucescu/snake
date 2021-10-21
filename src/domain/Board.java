package domain;

import config.Defaults;
import config.Settings;

import java.util.ArrayList;
import java.util.List;

import static domain.BoardObject.EMPTY;

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
                board[i][j] = EMPTY;
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

    public List<Coordinates> getAllEmptyPositions() {
        var result = new ArrayList<Coordinates>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j]==EMPTY) {
                    result.add(new Coordinates(i, j));
                }
            }
        }
        return result;
    }

    public List<Coordinates> getNeighbours(Coordinates coordinates) {
        var neighbours = new ArrayList<Coordinates>();
        int row = coordinates.row();
        int column = coordinates.column();
        for (var direction : Direction.values()) {
            int rowIndex = direction.getRowIndex();
            int columnIndex = direction.getColumnIndex();
            int neighbourRow = row + rowIndex;
            int neighbourColumn = column + columnIndex;
            if (0 <= neighbourRow && neighbourRow < rows && 0 <= neighbourColumn && neighbourColumn < columns) {
                neighbours.add(new Coordinates(neighbourRow, neighbourColumn));
            }
        }
        return neighbours;
    }
}
