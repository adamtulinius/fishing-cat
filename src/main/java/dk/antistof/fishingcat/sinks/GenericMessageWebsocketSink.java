package dk.antistof.fishingcat.sinks;

import dk.antistof.fishingcat.messages.GenericMessage;
import org.webbitserver.netty.WebSocketClient;

import java.util.List;

public class GenericMessageWebsocketSink implements Sink<GenericMessage> {
    private List<WebSocketClient> clients;

    public GenericMessageWebsocketSink() {

    }

    @Override
    public void absorb(GenericMessage genericMessage) {
        System.out.println(genericMessage.getClass().getName());
    }

    public void addClient(WebSocketClient client) {
        clients.add(client);
    }

    public void removeClient(WebSocketClient client) {
        while (clients.contains(client)) {
            clients.remove(client);
        }
    }
}
