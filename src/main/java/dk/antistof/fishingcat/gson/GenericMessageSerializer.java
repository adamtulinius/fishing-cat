package dk.antistof.fishingcat.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import dk.antistof.fishingcat.GsonSingleton;
import dk.antistof.fishingcat.messages.GenericMessage;

import java.lang.reflect.Type;

public class GenericMessageSerializer implements JsonSerializer<GenericMessage> {
    @Override
    public JsonElement serialize(GenericMessage genericMessage, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        if (genericMessage.getUuid() != null) jsonObject.add("uuid", new JsonPrimitive(genericMessage.getUuid().toString()));
        if (genericMessage.getExpiresAt() != null) jsonObject.add("expiresAt", new JsonPrimitive(genericMessage.getExpiresAt().getTime()));
        if (genericMessage.getPublishedAt() != null) jsonObject.add("publishedAt", new JsonPrimitive(genericMessage.getPublishedAt().getTime()));
        if (genericMessage.getContent() != null) jsonObject.add("content", GsonSingleton.getInstance().toJsonTree(genericMessage.getContent()));

        return jsonObject;
    }
}
