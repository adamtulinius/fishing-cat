package dk.antistof.bigscreen.messages;

public class PubSubMessage {
    private String channel;
    private Boolean subscribe;

    public PubSubMessage() {
        // left blank for gson reasons.
    }

    public PubSubMessage(String channel, Boolean subscribe) {
        this.channel = channel;
        this.subscribe = subscribe;
    }

    public String toString() {
        return channel + ": " + subscribe;
    }

    public String getChannel() {
        return channel;
    }

    public boolean getSubscribe() {
        return subscribe;
    }
}