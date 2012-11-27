package dk.antistof.fishingcat.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import dk.antistof.fishingcat.messages.Quote;

import java.lang.reflect.Type;

public class QuoteSerializer implements JsonSerializer<Quote> {
    @Override
    public JsonElement serialize(Quote quote, Type type, JsonSerializationContext jsonSerializationContext) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
