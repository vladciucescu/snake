package services;

import config.Settings;
import domain.Board;
import domain.Coordinates;
import domain.Player;
import domain.Snake;
import utils.BoardUtils;

import static config.Defaults.DEFAULT_APPLE_COUNT;
import static domain.BoardObject.*;
import static domain.Direction.*;

public class GameService {

    private final Player player;

    public GameService(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void placeSnake() {
        var board = player.getGameBoard();
        var snake = player.getSnake();
        validateInitialPosition(board, snake);
        board.setBoardObject(snake.getHead(), SNAKE_HEAD);
        snake.getBody().forEach(coordinates -> board.setBoardObject(coordinates, SNAKE_SEGMENT));
    }

    private void validateInitialPosition(Board board, Snake snake) {
        int rows = board.getRowCount();
        int columns = board.getColumnCount();
        Coordinates snakeHead = snake.getHead();
        int rowIndex = snake.getDirection().getRowIndex();
        int columnIndex = snake.getDirection().getColumnIndex();
        for (int i = 0; i < 3; i++) {
            int targetRow = snakeHead.row() + i * rowIndex;
            int targetColumn = snakeHead.column() + i * columnIndex;
            if (targetRow < 0 || targetRow >= rows || targetColumn < 0 || targetColumn >= columns) {
                throw new RuntimeException("Cannot place snake.");
            }
        }
    }

    public void placeApples() {
        int appleCount = Settings.getInstance().getAppleCount().orElse(DEFAULT_APPLE_COUNT);
        var board = player.getGameBoard();
        for (int i = 0; i < appleCount; i++) {
            var appleCoordinates = BoardUtils.getNewAppleCoordinates(board, BoardUtils::noApplesAround);
            board.setBoardObject(appleCoordinates, APPLE);
        }
    }

    public void moveUp() {
        player.getSnake().setDirection(UP);
        moveSnake(1);
    }

    public void moveDown() {
        player.getSnake().setDirection(DOWN);
        moveSnake(1);
    }

    public void moveLeft() {
        player.getSnake().setDirection(LEFT);
        moveSnake(1);
    }

    public void moveRight() {
        player.getSnake().setDirection(RIGHT);
        moveSnake(1);
    }

    public void moveSnake(int n) {
        var board = player.getGameBoard();
        var snake = player.getSnake();
        for (int i = 0; i < n; i++) {
            validateMove(board, snake);
            moveSnake1Step(board, snake);
            snake.removeTail();
            if (board.getBoardObject(snake.getHead()).equals(APPLE)) {
                snake.growTail();
                board.setBoardObject(snake.getHead(), APPLE);
            }
        }
    }

    private void validateMove(Board board, Snake snake) {
        throw new RuntimeException("Invalid");
    }

    private void moveSnake1Step(Board board, Snake snake) {
        board.setBoardObject(snake.getHead(), SNAKE_SEGMENT);
        board.setBoardObject(snake.getNextHeadPosition(), SNAKE_HEAD);
        board.setBoardObject(snake.getTail(), EMPTY);
    }
}
