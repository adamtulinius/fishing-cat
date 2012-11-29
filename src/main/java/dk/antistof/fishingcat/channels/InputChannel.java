package dk.antistof.fishingcat.channels;

import dk.antistof.fishingcat.messages.GenericMessage;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

public abstract class InputChannel<T extends GenericMessage> extends Channel<T> implements HttpHandler {

    public InputChannel() {
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
