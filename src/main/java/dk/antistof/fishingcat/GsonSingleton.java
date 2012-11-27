package dk.antistof.fishingcat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.antistof.fishingcat.gson.GenericMessageSerializer;
import dk.antistof.fishingcat.messages.GenericMessage;

public class GsonSingleton {
    private static Gson gsonInstance = null;

    public static Gson getInstance() {
        if (gsonInstance == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(GenericMessage.class, new GenericMessageSerializer());
            gsonInstance = gsonBuilder.create();
        }

        return gsonInstance;
    }
}
