package dk.antistof.fishingcat;

import dk.antistof.fishingcat.channels.ManagedChannel;
import dk.antistof.fishingcat.messages.GenericMessage;
import dk.antistof.fishingcat.messages.MessageEnvelope;

public class FishingCatManager extends ManagedChannel<MessageEnvelope<GenericMessage>, GenericMessage> {
    private boolean shutdown;
    private Foo<GenericMessage> currentMessage = null;

    public FishingCatManager() {
        shutdown = false;
    }

    public void shutdown() {
        shutdown = true;
    }

    @Override
    public void run() {
        while (!shutdown) {
            if (currentMessage != null) {
                emit(getNextMessage());
            }


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    @Override
    public void absorb(MessageEnvelope<GenericMessage> envelope) {
        addMessage(envelope.getPayload());
    }

    private synchronized void addMessage(GenericMessage genericMessage) {
        Foo<GenericMessage> foo = new Foo<GenericMessage>(genericMessage);
        if (currentMessage == null) {
            currentMessage = foo;
        } else {
            currentMessage.insert(foo);
        }
    }

    private synchronized GenericMessage getNextMessage() {
        currentMessage = currentMessage.getNext();
        return currentMessage.getPayload();
    }
}
