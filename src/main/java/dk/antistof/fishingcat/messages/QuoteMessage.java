package dk.antistof.fishingcat.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class QuoteMessage extends GenericMessage<String> {
    private String author;

    public QuoteMessage(String content) {
        super(content);
    }

    public QuoteMessage(UUID uuid, String content) {
        super(uuid, content);
    }

    @Override
    public String getType() {
        return "quote";
    }

    @JsonProperty
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
