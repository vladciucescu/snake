package ui;

import java.util.Collections;
import java.util.List;

public class Command {

    private final CommandType type;
    private final List<String> args;

    public Command(CommandType type, List<String> args) {
        this.type = type;
        this.args = args;
    }

    public Command(CommandType type) {
        this.type = type;
        this.args = Collections.emptyList();
    }

    public CommandType getType() {
        return type;
    }

    public List<String> getArgs() {
        return args;
    }
}
