package dk.antistof.fishingcat.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

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

    @JsonProperty
    public UUID getUuid() {
        return uuid;
    }

    @JsonProperty
    public T getContent() {
        return content;
    }

    @JsonProperty
    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    @JsonProperty
    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public boolean expired() {
        return expiresAt != null && new Date().compareTo(expiresAt) > 0;
    }

    public abstract String getType();
}
