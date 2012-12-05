package dk.antistof.fishingcat;

import dk.antistof.fishingcat.messages.GenericMessage;

public class Foo<T extends GenericMessage> {
    private T t;
    private Foo<T> next = this;

    public Foo(T t) {
        this.t = t;
    }

    public void pointAt(Foo<T> foo) {
        next = foo;
    }

    public T getPayload() {
        return t;
    }

    public Foo<T> getNext() {
        while (this != next && next.getPayload().expired()) {
            removeNext();
        }

        return next;
    }

    public void insert(Foo<T> foo) {
        if (foo != null) {
            foo.pointAt(next);
            next = foo;
        }
    }

    private void removeNext() {
        next = next.getNext();
    }
}
