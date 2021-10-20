package domain;

public class Player {

    private Board gameBoard;
    private final Snake snake;

    public Player(Snake snake) {
        this.gameBoard = new Board();
        this.snake = snake;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setGameBoard(Board gameBoard) {
        if (gameBoard == null) {
            gameBoard = new Board();
        }
        this.gameBoard = gameBoard;
    }
}
