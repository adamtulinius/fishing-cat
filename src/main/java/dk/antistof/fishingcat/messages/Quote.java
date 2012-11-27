package dk.antistof.fishingcat.messages;

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
}
