package dk.antistof.fishingcat;

import dk.antistof.fishingcat.channels.ManagedChannel;
import dk.antistof.fishingcat.channels.QuoteChannel;
import dk.antistof.fishingcat.channels.TimeChannel;
import dk.antistof.fishingcat.handlers.FishingCatWebSocketHandler;
import dk.antistof.fishingcat.messages.GenericMessage;
import dk.antistof.fishingcat.messages.MessageEnvelope;
import dk.antistof.fishingcat.sinks.GenericMessageWebsocketSink;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.handler.StaticFileHandler;

import java.util.concurrent.ExecutionException;

public class FishingCatServer {
    public static void main(String... args) throws ExecutionException, InterruptedException {
        WebServer server = WebServers.createWebServer(8080);
        FishingCatWebSocketHandler fishingCatWebSocketHandler = new FishingCatWebSocketHandler();

        TimeChannel timeChannel = new TimeChannel(5000);
        new Thread(timeChannel).start();

        QuoteChannel quoteChannel = new QuoteChannel();
        new Thread(quoteChannel).start();

        ManagedChannel<MessageEnvelope<GenericMessage>, GenericMessage> managedChannel =
                new ManagedChannel<MessageEnvelope<GenericMessage>, GenericMessage>() {
                    @Override
                    public void run() {
                        while (true) {
                            System.out.println(getSinks());
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                            }
                        }
                    }

                    @Override
                    public void absorb(MessageEnvelope<GenericMessage> envelope) {
                        emit(envelope.getPayload());
                    }
                };

        new Thread(managedChannel).start();

        managedChannel.addSink(new GenericMessageWebsocketSink());

        fishingCatWebSocketHandler.addChannel(timeChannel);
        fishingCatWebSocketHandler.addChannel(quoteChannel);
        server.add("/channels/quotes", quoteChannel);
        server.add("/ws", fishingCatWebSocketHandler);
        StaticFileHandler staticFileHandler = new StaticFileHandler("web");
        staticFileHandler.enableDirectoryListing(true);
        server.add(staticFileHandler);

        server.start().get();
    }
}
