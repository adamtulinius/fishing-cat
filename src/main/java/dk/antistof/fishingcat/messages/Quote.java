package dk.antistof.fishingcat.messages;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.UUID;

public class Quote extends GenericMessage<String> {
    private String author;

    public Quote(String content) {
        super(content);
    }

    public Quote(UUID uuid, String content) {
        super(uuid, content);
    }

    @Override
    public String getType() {
        return "quote";
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public JsonElement toJson() {
        JsonObject jsonObject = super.toJson().getAsJsonObject();
        if (author != null) jsonObject.add("author", new JsonPrimitive(author));

        return jsonObject;
    }
}
