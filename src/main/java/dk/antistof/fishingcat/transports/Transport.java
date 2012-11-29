package dk.antistof.fishingcat.transports;

import dk.antistof.fishingcat.messages.GenericMessage;

public interface Transport<I extends GenericMessage, O> {
    public void in(I message);
}
