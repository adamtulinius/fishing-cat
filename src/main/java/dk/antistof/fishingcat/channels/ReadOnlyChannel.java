package dk.antistof.fishingcat.channels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.antistof.fishingcat.jackson.ObjectMapperSingleton;
import dk.antistof.fishingcat.messages.GenericMessage;
import dk.antistof.fishingcat.messages.MessageEnvelope;
import org.webbitserver.WebSocketConnection;

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
        ObjectMapper objectMapper = ObjectMapperSingleton.getInstance();
        messages.put(message.getUuid(), message);
        try {
            String json = objectMapper.writeValueAsString(new MessageEnvelope<T>(getName(), message));
            System.out.println(json);
            for (WebSocketConnection client: clients) {
                client.send(json);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void sendAll(WebSocketConnection client) {
        ObjectMapper objectMapper = ObjectMapperSingleton.getInstance();
        for (UUID uuid : messages.keySet()) {
            T message = messages.get(uuid);
            if (message.expired()) {
                messages.remove(uuid);
            } else {
                try {
                    String json = objectMapper.writeValueAsString(message);
                    client.send(json);
                    //System.out.println(json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }

    public abstract String getName();
}
