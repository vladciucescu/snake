package domain;

import config.Settings;
import constants.Constants;
import utils.BoardUtils;

public class Board {
    private String[][] board;

    public Board() {
        int rows = Settings.getInstance().getRows().orElse(Constants.DEFAULT_ROW_COUNT);
        int columns = Settings.getInstance().getColumns().orElse(Constants.DEFAULT_COLUMN_COUNT);
        board = BoardUtils.createBoard(Constants.EMPTY, rows, columns);
    }

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }
}
