package dk.antistof.fishingcat;

import dk.antistof.fishingcat.channels.QuoteChannel;
import dk.antistof.fishingcat.channels.TimeChannel;
import dk.antistof.fishingcat.handlers.ApiHandler;
import dk.antistof.fishingcat.handlers.DistributionChannel;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.handler.StaticFileHandler;

import java.util.concurrent.ExecutionException;

public class Server {
    public static void main(String... args) throws ExecutionException, InterruptedException {
        WebServer server = WebServers.createWebServer(8080);
        DistributionChannel distributionChannel = new DistributionChannel();

        TimeChannel timeChannel = new TimeChannel(5000);
        new Thread(timeChannel).start();

        QuoteChannel quoteChannel = new QuoteChannel();
        new Thread(quoteChannel).start();

        distributionChannel.addChannel(timeChannel);
        distributionChannel.addChannel(quoteChannel);
        server.add("/channels/quotes", quoteChannel);
        server.add("/channels", new ApiHandler(distributionChannel));
        server.add("/ws", distributionChannel);
        server.add(new StaticFileHandler("web"));

        server.start().get();
    }
}
