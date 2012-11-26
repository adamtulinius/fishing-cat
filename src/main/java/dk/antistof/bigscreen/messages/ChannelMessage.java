package dk.antistof.bigscreen.messages;

public class ChannelMessage {
    private String channel;
    private GenericMessage<String> message;

    public ChannelMessage(String channel, GenericMessage<String> message) {
        this.channel = channel;
        this.message = message;
    }
}
