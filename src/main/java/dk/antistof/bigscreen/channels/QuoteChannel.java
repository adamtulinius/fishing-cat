package dk.antistof.bigscreen.channels;

import dk.antistof.bigscreen.messages.Quote;
import org.webbitserver.HttpRequest;

public class QuoteChannel extends Channel<Quote>{
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
}
