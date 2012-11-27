package dk.antistof.fishingcat.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.UUID;

public class TimeMessage extends GenericMessage<Long> {
    public TimeMessage(long content) {
        super(content);
    }

    public TimeMessage(UUID uuid, long content) {
        super(uuid, content);
        setExpiresAt(new Date(content));
    }

    @Override
    public String getType() {
        return "time";
    }
}
