package enums;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {
    CREATE_LIBRARY("create_library"),
    ADD_BOOK("add_book"),
    REMOVE_BOOK_COPY("remove_book_copy"),
    BORROW_BOOK("borrow_book"),
    BORROW_BOOK_COPY("borrow_book_copy"),
    RETURN_BOOK_COPY("return_book_copy"),
    PRINT_BORROWED("print_borrowed"),
    SEARCH("search"),
    EXIT("exit");

    private String command;
    private static final Map<String, CommandType> map = new HashMap<>();

    static {
        for (CommandType cmd: CommandType.values()) {
            map.put(cmd.command, cmd);
        }
    }

    CommandType(String command) {
        this.command = command;
    }

    public static CommandType getCommand(String command) {
        return map.get(command);
    }
}