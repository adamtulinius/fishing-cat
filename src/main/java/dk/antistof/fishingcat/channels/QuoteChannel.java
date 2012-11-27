package dk.antistof.fishingcat.channels;

import dk.antistof.fishingcat.messages.QuoteMessage;
import org.webbitserver.HttpRequest;

public class QuoteChannel extends Channel<QuoteMessage> implements Runnable {
    public QuoteChannel() {

    }

    @Override
    public String getName() {
        return "quotes";
    }

    @Override
    public QuoteMessage transformRequest(HttpRequest httpRequest) {
        QuoteMessage quoteMessage = new QuoteMessage(httpRequest.queryParam("quoteMessage"));
        quoteMessage.setAuthor(httpRequest.queryParam("author"));
        return quoteMessage;
    }

    public void run() {
        while (true) {
            QuoteMessage quoteMessage = new QuoteMessage("Næste gang tester jeg mit kode inden jeg pusher det.");
            quoteMessage.setAuthor("abr");
            publish(quoteMessage);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
