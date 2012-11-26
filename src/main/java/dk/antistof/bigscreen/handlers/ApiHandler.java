package dk.antistof.bigscreen.handlers;

import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

public class ApiHandler implements HttpHandler {
    private DistributionChannel distributionChannel;

    public ApiHandler(DistributionChannel distributionChannel) {
        this.distributionChannel = distributionChannel;
    }
    public void handleHttpRequest(HttpRequest httpRequest, HttpResponse httpResponse, HttpControl httpControl) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        distributionChannel.messageAll("Poke!");
        httpResponse.status(200).content("OK").end();
    }
}
