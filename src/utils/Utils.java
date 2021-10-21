package utils;

import ui.Command;
import ui.CommandType;

import java.util.List;

public class Utils {

    public static Command parseCommand(String input) {
        input = input.stripLeading();
        int position = input.indexOf(" ");
        if (position == -1) {
            var type = CommandType.get(input);
            return new Command(type);
        }
        String command = input.substring(0, position);
        var type = CommandType.get(command);
        var arguments = input.substring(position + 1).split(" ");
        return new Command(type, List.of(arguments));
    }

    public static void handleException(Exception e) {
        e.printStackTrace();
        System.out.println(e + "\nPlease try again");
    }
}
