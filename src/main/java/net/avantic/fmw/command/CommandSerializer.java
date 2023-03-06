package net.avantic.fmw.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CommandSerializer {

    public static String serializar(Command command) {
        try {
            ObjectMapper mapper = new JsonMapper()
                    .registerModule(new JavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(command);
        } catch (Throwable exception) {
            throw new RuntimeException(exception);
        }
    }
}
