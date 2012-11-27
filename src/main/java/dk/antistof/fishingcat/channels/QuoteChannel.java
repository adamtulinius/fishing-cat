package dk.antistof.fishingcat.channels;

import dk.antistof.fishingcat.messages.Quote;
import org.webbitserver.HttpRequest;

public class QuoteChannel extends Channel<Quote> implements Runnable {
    public QuoteChannel() {

    }

    @Override
    public String getName() {
        return "quotes";
    }

    @Override
    public Quote transformRequest(HttpRequest httpRequest) {
        Quote quote = new Quote(httpRequest.queryParam("quote"));
        quote.setAuthor(httpRequest.queryParam("author"));
        return quote;
    }

    @Override
    public void run() {
        while (true) {
            Quote quote = new Quote("Næste gang tester jeg mit kode inden jeg pusher det.");
            quote.setAuthor("abr");
            publish(quote);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
