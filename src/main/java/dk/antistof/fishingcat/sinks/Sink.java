package dk.antistof.fishingcat.sinks;

public interface Sink<T> {
    void absorb(T t);
}
