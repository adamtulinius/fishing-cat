package dk.antistof.bigscreen.channels;

import dk.antistof.bigscreen.messages.Time;

import java.util.Date;

public class TimeChannel extends ReadOnlyChannel<Time> implements Runnable {
    @Override
    public String getName() {
        return "time";
    }

    public void run() {
        while (true) {
            try {
                Time time = new Time(new Date().getTime());
                publish(time);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
