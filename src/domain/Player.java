package domain;

public class Player {

    private Board gameBoard;
    private Snake snake;

    public Player() {
        this.gameBoard = new Board();
        this.snake = new Snake();
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

    public void reset() {
        this.gameBoard = new Board();
        this.snake = new Snake();
    }
}
