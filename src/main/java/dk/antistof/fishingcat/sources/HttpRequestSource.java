package dk.antistof.fishingcat.sources;

import dk.antistof.fishingcat.sources.BasicSource;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

public class HttpRequestSource extends BasicSource<HttpRequest> implements HttpHandler {
    public void handleHttpRequest(HttpRequest httpRequest, HttpResponse httpResponse, HttpControl httpControl) throws Exception {
        try {
            emit(httpRequest);
            httpResponse.status(200).end();
        } catch (Exception e) {
            httpResponse.status(500);
            httpResponse.end();
        }
    }
}
