package conditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static conditions.Dinge.*;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 28.11.12
 * Time: 11:24
 */
public class Agent implements Runnable {
    String name;

    List<Dinge> dinges = new ArrayList<Dinge>(Arrays.asList(TOBACCO, MATCHES, PAPER));

    public Agent(String name) {
        this.name = name;
    }

    //Methods - @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public void legeDingeAufTisch(List<Dinge> topf) {
//        synchronized (topf){

        System.out.println(name + " versucht das lock zu bekommen");
        Holztisch.lock_TopfZugriff.lock();

        System.out.println(name + " hat das lock bekommen");
        Collections.shuffle(dinges);

        topf.addAll(dinges.subList(0, 2));
        System.out.println(name + ": der Agent hat neue Zutaten " + topf + " auf dem Tisch plaziert");
//                topf.wait();

        System.out.println(name + " benachrichtigt die Raucher, die auf der Warteschlange IsFull stehen");
        Holztisch.cond_IsFull.signalAll();

        System.out.println(name + " gibt das lock freigegeben und wartet auf fuelleTopf ");
        try {
            Holztisch.cond_fuelleTopf.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Holztisch.lock_TopfZugriff.unlock();
            return;
        }


        Holztisch.lock_TopfZugriff.unlock();
        System.out.println(name + " hat das lock freigegeben");

//        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            legeDingeAufTisch(Holztisch.topf);
        }

    }
}