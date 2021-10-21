package domain;

public class Player {

    private final Board gameBoard;
    private final Snake snake;

    public Player(Snake snake, Board board) {
        this.gameBoard = board;
        this.snake = snake;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public Snake getSnake() {
        return snake;
    }
}
