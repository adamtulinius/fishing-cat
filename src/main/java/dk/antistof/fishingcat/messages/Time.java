package dk.antistof.fishingcat.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class Time extends GenericMessage<Long> {

    public Time(long content) {
        super(content);
    }

    public Time(UUID uuid, long content) {
        super(uuid, content);
    }

    @Override
    public String getType() {
        return "time";
    }
}
