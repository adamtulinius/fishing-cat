package dk.antistof.bigscreen.messages;

import java.util.UUID;

public class Time extends GenericMessage<Long> {
    private long time;

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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
