package services;

import domain.Board;
import domain.Coordinates;
import domain.Player;
import domain.Snake;
import utils.BoardUtils;

import static domain.BoardObject.APPLE;
import static domain.BoardObject.SNAKE_SEGMENT;
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
        board.placeSnake(snake);
    }

    private void validateInitialPosition(Board board, Snake snake) {
        boolean headOk = board.isOnBoard(snake.getHead());
        boolean bodyOk = snake.getBody().allMatch(board::isOnBoard);
        if (!headOk || !bodyOk) {
            throw new RuntimeException("There's not enough space to draw a snake given this start position and direction.");
        }
    }

    public void placeApple() {
        var board = player.getGameBoard();
        var appleCoordinates = BoardUtils.getNewAppleCoordinates(board, BoardUtils::noAppleNeighbours);
        board.setBoardObject(appleCoordinates, APPLE);
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
            boolean eatApple = board.getBoardObject(snake.getNextHeadPosition()).equals(APPLE);
            if (eatApple) {
                snake.growTail();
                placeApple();
            }
            snake.move();
            board.placeSnake(snake);
        }
    }

    private void validateMove(Board board, Snake snake) {
        Coordinates nextPosition = snake.getNextHeadPosition();
        if (!board.isOnBoard(nextPosition)) {
            throw new InvalidMoveException("Snake hits the wall!");
        }
        if (board.getBoardObject(nextPosition).equals(SNAKE_SEGMENT)) {
            throw new InvalidMoveException("Snake hits the snake!");
        }
    }
}
