package conditions;

import services.RandomManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static conditions.Dinge.*;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 28.11.12
 * Time: 11:24
 */
public class Holztisch {

    final private static long programmWarteZeit = RandomManager.longNumber(7500, 3750);

    static Agent agent = new Agent("Agent");
    static Raucher raucher = Raucher.create(TOBACCO, "Raucher 1");
    static Raucher raucher1 = Raucher.create(MATCHES, "Raucher 2");
    static Raucher raucher2 = Raucher.create(PAPER, "Raucher 3");
    static List<Dinge> topf = new ArrayList<Dinge>();
    final static Lock lock_TopfZugriff = new ReentrantLock(true);
    static Condition cond_IsFull = lock_TopfZugriff.newCondition(),
            cond_fuelleTopf = lock_TopfZugriff.newCondition();

    public static void main(String[] args) {
        List<Raucher> rauchers = new ArrayList<Raucher>(Arrays.asList(raucher, raucher1, raucher2));
        List<Thread> threads = new ArrayList<Thread>();
        threads.add(new Thread(agent));

        __programm_info(programmWarteZeit);

        for (Raucher r : rauchers) {
            Thread thread = new Thread(r);
            threads.add(thread);
            System.out.println(r + " ist " + thread);
        }


        for (Thread thread : threads) {
            thread.start();
        }

        try {
            Thread.sleep(programmWarteZeit);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        for (Thread thread : threads) {
            thread.interrupt();
            System.out.println(thread + " ist tot");
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        System.out.println("- - - Vorbei - - -\nTisch ist abgebrannt!\nNächstesmal am besten eine feuerfeste Unterlage benutzen!");
//        for (Thread thread : threads) {
//            System.out.println(thread + " alive " +thread.isAlive());
//            System.out.println(thread + " interrupted: "+thread.isInterrupted());
//        }

    }

    private static void __programm_info(long programmWarteZeit) {
        long programmWarteZeitInSec = programmWarteZeit / 1000,
                programmWarteZeitInSexInMin = programmWarteZeitInSec / 60;
        System.out.println("Vorgegebene Programmlaufzeit beträgt: " + programmWarteZeitInSec + " sec -> " + programmWarteZeitInSexInMin + " min");
    }
}
