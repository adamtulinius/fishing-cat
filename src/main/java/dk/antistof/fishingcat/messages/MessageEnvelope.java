package dk.antistof.fishingcat.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dk.antistof.fishingcat.jackson.MessageEnvelopeSerializer;

@JsonSerialize(using = MessageEnvelopeSerializer.class)
public class MessageEnvelope<T> {
    private String channel;
    private GenericMessage<T> payload;

    public MessageEnvelope() {

    }

    public MessageEnvelope(String channel, GenericMessage<T> payload) {
        this.channel = channel;
        this.payload = payload;
    }

    public String getChannel() {
        return channel;
    }

    public GenericMessage<T> getPayload() {
        return payload;
    }

    public String getType() {
        return payload.getType();
    }
}
