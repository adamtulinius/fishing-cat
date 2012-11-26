package dk.antistof.bigscreen.channels;

import com.google.gson.Gson;
import dk.antistof.bigscreen.messages.GenericMessage;
import dk.antistof.bigscreen.messages.MessageEnvelope;
import org.webbitserver.WebSocketConnection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class ReadOnlyChannel<T extends GenericMessage> {
    private transient List<WebSocketConnection> clients;
    private transient Map<UUID, T> messages;

    public ReadOnlyChannel() {
        clients = new ArrayList<WebSocketConnection>();
        messages = new HashMap<UUID, T>();
    }

    public void subscribe(WebSocketConnection client) {
        if (!clients.contains(client)) {
            clients.add(client);
        }
    }

    public void unsubscribe(WebSocketConnection client) {
        clients.remove(client);
    }

    public void publish(T message) {
        System.out.println(message.getClass());
        messages.put(message.getUuid(), message);
        Gson gson = new Gson();
        String json = gson.toJson(new MessageEnvelope<T>(getName(), message));
        System.out.println(json);
        for (WebSocketConnection client: clients) {
            client.send(json);
        }
    }

    public void sendAll(WebSocketConnection client) {
        Gson gson = new Gson();
        for (UUID uuid : messages.keySet()) {
            T message = messages.get(uuid);
            if (message.expired()) {
                messages.remove(uuid);
            } else {
                String json = gson.toJson(message);
                client.send(json);
                System.out.println(json);
            }
        }
    }

    public abstract String getName();
}
