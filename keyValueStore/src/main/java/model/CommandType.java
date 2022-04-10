package model;

import java.io.IOException;

public enum CommandType {
    PUT("put"),
    GET("get"),
    SEARCH("search"),
    DELETE("delete"),
    KEYS("keys"),
    EXIT("exit");

    private String alias;

    CommandType(String name) {
        this.alias = name;
    }

    public static CommandType getType(String name) throws Exception {
        for (CommandType ct:
             CommandType.values()) {
            if(name.equals(ct.alias)) return ct;
        }
        throw new IOException("Command type for " + name + " not found");
    }
}
