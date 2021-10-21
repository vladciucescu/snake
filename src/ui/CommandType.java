package ui;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum CommandType {
    MOVE("1. Move <n>"),
    UP("2. Up"),
    DOWN("3. Down"),
    LEFT("4. Left"),
    RIGHT("5. Right"),
    EXIT("6. Exit"),
    INVALID("");

    private static final Map<String, CommandType> enumMap = Stream.of(CommandType.values()).collect(toMap(Enum::name, identity()));

    public static CommandType get(String value) {
        return enumMap.getOrDefault(value.toUpperCase(), INVALID);
    }

    private final String menuOption;

    CommandType(String menuOption) {
        this.menuOption = menuOption;
    }

    public String getMenuOption() {
        return menuOption;
    }
}
