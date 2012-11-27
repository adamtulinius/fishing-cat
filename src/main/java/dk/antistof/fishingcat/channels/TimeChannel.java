package dk.antistof.fishingcat.channels;

import dk.antistof.fishingcat.messages.Time;

import java.util.Date;

public class TimeChannel extends ReadOnlyChannel<Time> implements Runnable {
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
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                Time time = new Time(new Date().getTime());
                System.out.println(time.getContent());
                publish(time);
                Thread.sleep(interval);
            } catch (InterruptedException e) {
            }
        }
    }
}
