package dk.antistof.fishingcat.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.antistof.fishingcat.ObjectMapperSingleton;
import dk.antistof.fishingcat.messages.PubSubMessage;
import dk.antistof.fishingcat.channels.ReadOnlyChannel;
import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebSocketConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistributionChannel extends BaseWebSocketHandler {
    private Map<String, ReadOnlyChannel> channels;
    private List<WebSocketConnection> connections;

    public DistributionChannel() {
        channels = new HashMap<String, ReadOnlyChannel>();
        connections = new ArrayList<WebSocketConnection>();
    }

    public void addChannel(ReadOnlyChannel channel) {
        channels.put(channel.getName(), channel);
    }

    public void onOpen(WebSocketConnection client) {
        connections.add(client);
    }

    public void onMessage(WebSocketConnection client, String message) {
        ObjectMapper objectMapper = ObjectMapperSingleton.getInstance();
        try {
            PubSubMessage pubSubMessage = objectMapper.readValue(message, PubSubMessage.class);
            System.out.println(pubSubMessage);
            if (channels.containsKey(pubSubMessage.getChannel())) {
                if (pubSubMessage.getSubscribe()) {
                    channels.get(pubSubMessage.getChannel()).subscribe(client);
                } else {
                    channels.get(pubSubMessage.getChannel()).unsubscribe(client);
                }
            }
            client.send("OK");
        } catch (Exception e) {
            e.printStackTrace();
            client.send("Failed");
        }
    }

    public void messageAll(String message) {
        for (WebSocketConnection connection: connections) {
            connection.send(message);
        }
    }
}
