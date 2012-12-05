package dk.antistof.fishingcat.channels;

import dk.antistof.fishingcat.sinks.Sink;
import dk.antistof.fishingcat.sources.AsyncSource;

public abstract class ManagedChannel<I, O> extends AsyncSource<O> implements Sink<I>, Runnable {
}
