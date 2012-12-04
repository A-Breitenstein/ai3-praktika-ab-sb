package monitore;

import services.RandomManager;

import java.util.*;

import static monitore.Dinge.*;
/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 28.11.12
 * Time: 11:24
 */
public class Holztisch {

    final private static long programmWarteZeit = RandomManager.longNumber(75000, 37500);

    static Agent agent = new Agent("Agent");
    static Raucher raucher = Raucher.create(TOBACCO,"Raucher 1");
    static Raucher raucher1 = Raucher.create(MATCHES,"Raucher 2");
    static Raucher raucher2 = Raucher.create(PAPER,"Raucher 3");
    static List<Dinge> topf = new ArrayList<Dinge>();

    public static void main(String[] args) {
        List<Raucher> rauchers = new ArrayList<Raucher>(Arrays.asList(raucher,raucher1,raucher2));
        List<Thread> threads = new ArrayList<Thread>();
        threads.add(new Thread(agent));

        __programm_info(programmWarteZeit);

        for (Raucher r : rauchers) {
            threads.add(new Thread(r));
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
//            System.out.println(thread + " ist aus");
        }
        System.out.println("- - - Vorbei - - -\nTisch ist abgebrannt!\nNächstesmal am besten eine feuerfeste Unterlage benutzen!");


    }

    private static void __programm_info(long programmWarteZeit) {
        long programmWarteZeitInSec = programmWarteZeit/1000,
                programmWarteZeitInSexInMin = programmWarteZeitInSec/60;
        System.out.println("Vorgegebene Programmlaufzeit beträgt: " + programmWarteZeitInSec + " sec -> " + programmWarteZeitInSexInMin + " min");
    }
}
