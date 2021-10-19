package utils;

import domain.Board;

public class BoardUtils {

    public static String[][] createBoard(String initialValue, int numberOfRows, int numberOfColumns) {
        var board = new String[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                board[i][j] = initialValue;
            }
        }
        return board;
    }

    public static void printBoardV4(Board playerBoard) {

    }
}
