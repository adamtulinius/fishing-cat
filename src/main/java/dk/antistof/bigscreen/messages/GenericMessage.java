package dk.antistof.bigscreen.messages;

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

    public abstract String getType();
}
