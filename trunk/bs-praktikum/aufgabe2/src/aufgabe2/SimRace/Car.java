package aufgabe2.SimRace;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Date: 12.11.12
 * Time: 22:31
 */
public class Car implements Runnable {
    @Override
    public void run() {
        Thread cThread = Thread.currentThread();
        System.out.println("Hallo ich bin: " + cThread.getName());
        long teeTimer = 2000L;
        try {
            Thread.currentThread().wait(teeTimer);
            System.out.println(cThread + " hat " + teeTimer + " gewartet!");
        } catch (InterruptedException e) {
            System.out.println(cThread.getName() + " war zu ungeduldig und hat nicht gewartet!");
        }
        System.out.println(cThread.getName() + " ist fertig!");
    }
}
