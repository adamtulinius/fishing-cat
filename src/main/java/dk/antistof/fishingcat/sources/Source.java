package dk.antistof.fishingcat.sources;

import dk.antistof.fishingcat.sinks.Sink;

public interface Source<T> {
    void addSink(Sink<T> sink);
    void emit(T t);
}
