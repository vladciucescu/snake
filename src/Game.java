import domain.Player;
import services.GameService;
import ui.UI;

public class Game {

    public static void main(String ...args) {
        var player = new Player();
        var gameService = new GameService(player);
        var ui = new UI(gameService);
        ui.run();
    }
}
