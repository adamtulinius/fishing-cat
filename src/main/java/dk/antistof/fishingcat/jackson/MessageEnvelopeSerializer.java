package dk.antistof.fishingcat.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dk.antistof.fishingcat.messages.MessageEnvelope;

import java.io.IOException;

public class MessageEnvelopeSerializer extends JsonSerializer<MessageEnvelope> {
    @Override
    public void serialize(MessageEnvelope messageEnvelope, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("channel", messageEnvelope.getChannel());
        jsonGenerator.writeStringField("type", messageEnvelope.getPayload().getType());
        jsonGenerator.writeObjectField("payload", messageEnvelope.getPayload());
        jsonGenerator.writeEndObject();
    }
}
