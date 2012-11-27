package dk.antistof.fishingcat.messages;

public class SubscriptionMessage {
    private String channel;
    private Boolean subscribe;

    public SubscriptionMessage() {

    }

    public SubscriptionMessage(String channel, Boolean subscribe) {
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
