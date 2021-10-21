package utils;

import domain.Board;
import domain.BoardObject;
import domain.Coordinates;

import java.util.Random;
import java.util.function.BiPredicate;

import static domain.BoardObject.APPLE;
import static java.util.stream.Collectors.toList;

public class BoardUtils {

    public static void printBoard(Board board) {
        var header = "";
        for (int columnIndex = 0; columnIndex < board.getColumnCount(); columnIndex++) {
            var spacing = columnIndex < 9 ? "   " : "  ";
            header += (columnIndex + 1) + spacing;
        }
        var grid = "     " + header;
        for (int rowIndex = 0; rowIndex < board.getRowCount(); rowIndex++) {
            var spacing = rowIndex < 9 ? "  | " : " | ";
            var rowStrings = board.getRow(rowIndex).stream().map(BoardObject::getSymbol).collect(toList());
            grid += "\n" + (rowIndex + 1) + spacing + String.join(" | ", rowStrings) + " | ";
        }
        System.out.println(grid);
    }

    public static Coordinates getNewAppleCoordinates(Board board, BiPredicate<Board, Coordinates> condition) {
        var random = new Random();
        var emptyPositions = board.getAllEmptyPositions();
        int size = emptyPositions.size();
        Coordinates randomPosition = emptyPositions.get(random.nextInt(size));
        int tries = 0;
        while (!condition.test(board, randomPosition) && tries < size) {
            tries++;
            randomPosition = emptyPositions.get(random.nextInt(size));
        }
        if (tries == size) {
            throw new RuntimeException("Cannot find empty position for apple");
        }
        return randomPosition;
    }

    public static boolean noApplesAround(Board board, Coordinates coordinates) {
        for (Coordinates neighbour : board.getNeighbours(coordinates)) {
            if (board.getBoardObject(neighbour).equals(APPLE)) return false;
        }
        return true;
    }

}
