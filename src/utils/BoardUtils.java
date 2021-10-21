package utils;

import domain.Board;
import domain.Coordinates;

import java.util.Random;
import java.util.function.BiPredicate;

import static domain.BoardObject.APPLE;

public class BoardUtils {

    public static void printBoard(Board board) {

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
