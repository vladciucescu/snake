package ui;

import services.GameService;
import utils.BoardUtils;
import utils.Utils;

import java.util.Scanner;

import static ui.CommandType.EXIT;
import static ui.CommandType.INVALID;

public class UI {

    public static final String INVALID_COMMAND = "Invalid command, please try again.";
    public static final String GOODBYE_MESSAGE = "We are sorry to see you go..";

    private GameService gameService;

    public UI(GameService gameService) {
        this.gameService = gameService;
    }

    public void run() {
        showMenu();
        drawSnakeAndApples();
        try (var in = new Scanner(System.in)) {
            processCommands(in);
        } catch (Exception e) {
            Utils.handleException(e);
        }
    }

    private void showMenu() {
        for (var commandType : CommandType.values()) {
            System.out.println(commandType.getMenuOption());
        }
    }

    private void drawSnakeAndApples() {
        gameService.placeSnake();
        gameService.placeApples();
        printBoard();
    }

    //naspa
    private void processCommands(Scanner in) {
        while (true) {
            System.out.print("Command");
            var command = Utils.parseCommand(in.nextLine());
            if (command.getType().equals(EXIT)) {
                System.out.println(GOODBYE_MESSAGE);
                break;
            }
            if (command.getType().equals(INVALID)) {
                System.out.println(INVALID_COMMAND);
                continue;
            }
            try {
                executeCommand(command);
            } catch (Exception e) {
                Utils.handleException(e);
            }
        }
    }

    private void executeCommand(Command command) {
        switch (command.getType()) {
            case MOVE -> {
                if (command.getArgs().isEmpty()) move(1);
                else {
                    int n = Integer.parseInt(command.getArgs().get(0));
                    move(n);
                }
            }
            case UP -> up();
            case DOWN -> down();
            case LEFT -> left();
            case RIGHT -> right();
            default -> throw new RuntimeException("Unknown command");
        }
    }

    private void move(int n) {
        gameService.moveSnake(n);
        printBoard();
    }

    private void up() {
        gameService.moveUp();
        printBoard();
    }

    private void down() {
        gameService.moveDown();
        printBoard();
    }

    private void left() {
        gameService.moveLeft();
        printBoard();
    }

    private void right() {
        gameService.moveRight();
        printBoard();
    }

    private void printBoard() {
        var playerBoard = gameService.getPlayer().getGameBoard();
        BoardUtils.printBoardV4(playerBoard);
    }
}
