package dk.antistof.bigscreen.handlers;

import com.google.gson.Gson;
import dk.antistof.bigscreen.messages.PubSubMessage;
import dk.antistof.bigscreen.channels.ReadOnlyChannel;
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
        Gson gson = new Gson();
        try {
            PubSubMessage pubSubMessage = gson.fromJson(message, PubSubMessage.class);
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
            client.send("Failed");
        }
    }

    public void messageAll(String message) {
        for (WebSocketConnection connection: connections) {
            connection.send(message);
        }
    }
}
