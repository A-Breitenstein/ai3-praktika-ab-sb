package monitore;

import java.util.*;

import static monitore.Dinge.*;

/**
 * Created with IntelliJ IDEA.
 * User: abg667
 * Date: 28.11.12
 * Time: 11:24
 */
public class Agent implements Runnable {
    String name;

    List<Dinge> dinges = new ArrayList<Dinge>(Arrays.asList(TOBACCO,MATCHES,PAPER));

    public Agent(String name) {
        this.name = name;
    }

    //Methods - @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public void legeDingeAufTisch(List<Dinge> topf){
        synchronized (topf){
            Collections.shuffle(dinges);

            topf.addAll(dinges.subList(0,2));
            System.out.println(name+ ": der Agent hat neue Zutaten "+topf+" auf dem Tisch plaziert");
            try {
               topf.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }
    }

    @Override
    public void run() {
        while(true){
            legeDingeAufTisch(Holztisch.topf);
        }

    }
}
