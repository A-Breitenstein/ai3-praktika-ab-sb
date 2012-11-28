package monitore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static monitore.Dinge.*;
/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 28.11.12
 * Time: 11:24
 */
public class Holztisch {

    static Agent agent = new Agent("Agent");
    static Raucher raucher = Raucher.create(TOBACCO,"Raucher 1");
    static Raucher raucher1 = Raucher.create(MATCHES,"Raucher 2");
    static Raucher raucher2 = Raucher.create(PAPER,"Raucher 3");
    static List<Dinge> topf = new ArrayList<Dinge>();

    public static void main(String[] args) {
        List<Raucher> rauchers = new ArrayList<Raucher>(Arrays.asList(raucher,raucher1,raucher2));
        List<Thread> threads = new ArrayList<Thread>();
        threads.add(new Thread(agent));


        for (Raucher r : rauchers) {
            threads.add(new Thread(r));
        }

        for (Thread thread : threads) {
            thread.start();
        }


    }
}
