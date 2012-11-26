package dk.antistof.bigscreen.messages;

public class MessageEnvelope<T> {
    private String channel;
    private GenericMessage<T> message;
    private String type;

    public MessageEnvelope() {

    }

    public MessageEnvelope(String channel, GenericMessage<T> message) {
        this.channel = channel;
        this.message = message;
        this.type = message.getType();
    }
}
