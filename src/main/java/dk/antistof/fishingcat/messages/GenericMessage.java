package dk.antistof.fishingcat.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import dk.antistof.fishingcat.GsonSingleton;

import java.util.Date;
import java.util.UUID;

public abstract class GenericMessage<T> {
    private UUID uuid;
    private T content;
    private Date expiresAt;
    private Date publishedAt;

    public GenericMessage(T content) {
        this(null, content);
    }

    public GenericMessage(UUID uuid, T content) {
        if (uuid == null) {
            this.uuid = UUID.randomUUID();
        } else {
            this.uuid = uuid;
        }
        this.content = content;
        this.publishedAt = new Date();
    }

    public UUID getUuid() {
        return uuid;
    }

    public T getContent() {
        return content;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public boolean expired() {
        return expiresAt != null && new Date().compareTo(expiresAt) > 0;
    }

    @JsonIgnore
    public abstract String getType();

    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        if (uuid != null) jsonObject.add("uuid", new JsonPrimitive(uuid.toString()));
        if (expiresAt != null) jsonObject.add("expiresAt", new JsonPrimitive(expiresAt.getTime()));
        if (publishedAt != null) jsonObject.add("publishedAt", new JsonPrimitive(publishedAt.getTime()));
        if (content != null) jsonObject.add("content", GsonSingleton.getInstance().toJsonTree(content));

        return jsonObject;
    }
}
