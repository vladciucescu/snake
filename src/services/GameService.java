package services;

import domain.Player;

import static domain.Direction.*;

public class GameService {

    private Player player;

    public GameService(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void placeSnake() {

    }

    public void placeApples() {

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
        var gameBoard = player.getGameBoard();
        var direction = player.getSnake().getDirection();
        for (int i = 0; i < n; i++) {

        }
    }
}
