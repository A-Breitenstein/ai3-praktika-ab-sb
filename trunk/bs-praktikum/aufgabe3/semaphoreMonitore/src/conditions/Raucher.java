package conditions;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 28.11.12
 * Time: 11:24
 */
public class Raucher implements Runnable {
    private String name;
    private Dinge ding;

    private Raucher(Dinge ding, String name) {
        this.ding = ding;
        this.name = name;
    }

    public static Raucher create(Dinge ding, String name) {
        return new Raucher(ding, name);
    }


    @Override
    public void run() {
        List<Dinge> topf = Holztisch.topf;

        while (!Thread.currentThread().isInterrupted()) {


            System.out.println(name + " versucht das lock zu bekommen");
            Holztisch.lock_TopfZugriff.lock();
            System.out.println(name + " hat das lock bekommen");
//            synchronized (topf)

            if (alleGutenDingeSindDrei(topf)) {
                rollen(topf);
                try {
                    rauchen();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    Holztisch.lock_TopfZugriff.unlock();
                    break;
                }
//                    topf.notifyAll();
                Holztisch.cond_fuelleTopf.signal();
            } else if (!topf.isEmpty()) {
                System.out.println(name + ": #FAIL# aus diesen Zutaten " + topf + " und meiner Zutat " + ding + " kann ich mir keine Zigarette drehen");
            }

            System.out.println(name + " gibt das lock freigegeben und wartet auf isFull ");
            try {
//              topf.wait();
                Holztisch.cond_IsFull.await();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Holztisch.lock_TopfZugriff.unlock();
                break;
            }
            Holztisch.lock_TopfZugriff.unlock();
            System.out.println(name + " hat das lock freigegeben");
        }
    }

    private void rauchen() throws InterruptedException {

        System.out.println(name + ": zündet sich seine Zigarette an ....");
        Thread.sleep(2700);
        System.out.println(name + ": wirft die Zigarette auf den Boden");
        Thread.sleep(500);
        System.out.println(name + ": ...stampf...");
        Thread.sleep(500);
        System.out.println(name + ": ...stampf...");
        Thread.sleep(500);
        System.out.println(name + ": is aus");
        Thread.sleep(1000);


    }

    private void rollen(List<Dinge> zutaten) {
        System.out.println(name + ": Rollt sich seine Zigarette mit den Zutaten: " + zutaten + " und seinem " + ding);
        zutaten.clear();

    }

    private boolean alleGutenDingeSindDrei(List<Dinge> topf) {
        return !topf.contains(ding) && !topf.isEmpty();
    }
}
