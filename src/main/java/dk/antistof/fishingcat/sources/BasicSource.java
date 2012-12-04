package dk.antistof.fishingcat.sources;

import dk.antistof.fishingcat.sinks.Sink;

import java.util.LinkedList;
import java.util.List;

public class BasicSource<T> implements Source<T> {
    private List<Sink<T>> sinks;

    public BasicSource() {
        sinks = new LinkedList<Sink<T>>();
    }

    public void addSink(Sink<T> sink) {
        sinks.add(sink);
    }

    public void emit(T t) {
        for (Sink<T> sink : sinks) {
            sink.absorb(t);
        }
    }
}