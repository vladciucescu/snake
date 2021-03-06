import domain.Board;
import domain.Direction;
import domain.Player;
import domain.Snake;
import services.GameService;
import ui.UI;

public class Game {

    public static void main(String... args) {
        var snake = new Snake(Direction.UP);
        var board = new Board();
        var player = new Player(snake, board);
        var gameService = new GameService(player);
        var ui = new UI(gameService);
        ui.run();
    }
}
