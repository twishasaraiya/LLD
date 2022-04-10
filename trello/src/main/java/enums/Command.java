package enums;

import java.io.IOException;

public enum Command {
    ADD_MEMBER("ADD_MEMBER"),
    ASSIGN("ASSIGN"),
    BOARD("BOARD"),
    CARD("CARD"),
    CLONE("CLONE"),
    CREATE("CREATE"),
    DELETE("DELETE"),
    DESCRIPTION("description"),
    EXIT("exit"),
    LIST("LIST"),
    MOVE("MOVE"),
    NAME("name"),
    PRIVACY("privacy"),
    REMOVE_MEMBER("REMOVE_MEMBER"),
    SHOW("SHOW"),
    TAG("TAG"),
    UNASSIGN("UNASSIGN"),
    USER("USER");

    private String commandType;

    Command(String commandType) {
        this.commandType = commandType;
    }

    public static Command getType(String commandType) {
        for (Command c : Command.values()) {
            if (commandType.equalsIgnoreCase(c.commandType)) {
                return c;
            }
        }
        return null;
    }
}
