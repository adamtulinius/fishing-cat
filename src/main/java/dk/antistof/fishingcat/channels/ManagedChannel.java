package dk.antistof.fishingcat.channels;

import dk.antistof.fishingcat.sinks.Sink;
import dk.antistof.fishingcat.sources.AsyncSource;

public class ManagedChannel<T> extends AsyncSource<T> implements Sink<T> {
    public void absorb(T t) {
        emit(t);
    }
}
