package dk.antistof.fishingcat;

import dk.antistof.fishingcat.channels.QuoteChannel;
import dk.antistof.fishingcat.channels.TimeChannel;
import dk.antistof.fishingcat.handlers.FishingCatWebSocketHandler;
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

        fishingCatWebSocketHandler.addChannel(timeChannel);
        fishingCatWebSocketHandler.addChannel(quoteChannel);
        server.add("/channels/quotes", quoteChannel);
        server.add("/ws", fishingCatWebSocketHandler);
        server.add(new StaticFileHandler("web"));

        server.start().get();
    }
}
