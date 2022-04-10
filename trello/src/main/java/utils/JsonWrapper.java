package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonWrapper {
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    public static String jsonString(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
}
