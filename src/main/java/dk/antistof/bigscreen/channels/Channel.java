package dk.antistof.bigscreen.channels;

import dk.antistof.bigscreen.messages.GenericMessage;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

public abstract class Channel<T extends GenericMessage> extends ReadOnlyChannel<T> implements HttpHandler {

    public Channel() {
        super();
    }

    public abstract T transformRequest(HttpRequest httpRequest);

    public void handleHttpRequest(HttpRequest httpRequest, HttpResponse httpResponse, HttpControl httpControl) throws Exception {
        try {
            T t = transformRequest(httpRequest);
            publish(t);
            httpResponse.status(200).content("OK").end();
        } catch (Exception e) {
            httpResponse.status(500);
            httpResponse.end();
        }
    }
}
