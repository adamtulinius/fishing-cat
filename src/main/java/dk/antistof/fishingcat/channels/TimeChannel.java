package dk.antistof.fishingcat.channels;

import dk.antistof.fishingcat.messages.TimeMessage;

import java.util.Date;

public class TimeChannel extends ReadOnlyChannel<TimeMessage> implements Runnable {
    private long interval = 1000;

    public TimeChannel() {
        super();
    }

    public TimeChannel(long interval) {
        super();
        this.interval = interval;
    }

    @Override
    public String getName() {
        return "time";
    }

    public void run() {
        while (true) {
            try {
                TimeMessage timeMessage = new TimeMessage(new Date().getTime());
                publish(timeMessage);
                Thread.sleep(interval);
            } catch (InterruptedException e) {
            }
        }
    }
}
