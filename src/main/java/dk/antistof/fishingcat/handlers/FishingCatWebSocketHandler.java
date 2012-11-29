package dk.antistof.fishingcat.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.antistof.fishingcat.channels.Channel;
import dk.antistof.fishingcat.jackson.ObjectMapperSingleton;
import dk.antistof.fishingcat.messages.SubscriptionMessage;
import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebSocketConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FishingCatWebSocketHandler extends BaseWebSocketHandler {
    private Map<String, Channel> channels;
    private List<WebSocketConnection> connections;

    public FishingCatWebSocketHandler() {
        channels = new HashMap<String, Channel>();
        connections = new ArrayList<WebSocketConnection>();
    }

    public void addChannel(Channel channel) {
        channels.put(channel.getName(), channel);
    }

    public void onOpen(WebSocketConnection client) {
        connections.add(client);
    }

    public void onMessage(WebSocketConnection client, String message) {
        ObjectMapper objectMapper = ObjectMapperSingleton.getInstance();
        try {
            SubscriptionMessage subscriptionMessage = objectMapper.readValue(message, SubscriptionMessage.class);
            System.out.println(subscriptionMessage);
            if (channels.containsKey(subscriptionMessage.getChannel())) {
                if (subscriptionMessage.getSubscribe()) {
                    channels.get(subscriptionMessage.getChannel()).subscribe(client);
                } else {
                    channels.get(subscriptionMessage.getChannel()).unsubscribe(client);
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
