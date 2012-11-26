package dk.antistof.bigscreen;

import dk.antistof.bigscreen.channels.QuoteChannel;
import dk.antistof.bigscreen.channels.TimeChannel;
import dk.antistof.bigscreen.handlers.ApiHandler;
import dk.antistof.bigscreen.handlers.DistributionChannel;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.handler.StaticFileHandler;

import java.util.concurrent.ExecutionException;

public class Server {
    public static void main(String... args) throws ExecutionException, InterruptedException {
        TimeChannel timeChannel = new TimeChannel(5000);
        Thread timeChannelThread = new Thread(timeChannel);
        timeChannelThread.start();

        WebServer server = WebServers.createWebServer(8080);
        DistributionChannel distributionChannel = new DistributionChannel();
        QuoteChannel quoteChannel = new QuoteChannel();
        distributionChannel.addChannel(timeChannel);
        distributionChannel.addChannel(quoteChannel);
        server.add("/channels/quotes", quoteChannel);
        server.add("/channels", new ApiHandler(distributionChannel));
        server.add("/ws", distributionChannel);
        server.add(new StaticFileHandler("web"));

        server.start().get();
    }
}
