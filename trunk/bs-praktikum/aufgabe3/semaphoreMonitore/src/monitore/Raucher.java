package monitore;

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

    private Raucher(Dinge ding,String name) {
        this.ding = ding;
        this.name = name;
    }

    public static Raucher create(Dinge ding,String name) {
        return new Raucher(ding,name);
    }


    @Override
    public void run() {
        List<Dinge> topf = Holztisch.topf;
        while (true){
            synchronized (topf){

                if(alleGutenDingeSindDrei(topf)){
                    rollen(topf);
                    rauchen();
                    topf.notifyAll();
                }else if(!topf.isEmpty()){
                    System.out.println(name+": #FAIL# aus diesen Zutaten "+topf+" und meiner Zutat "+ding+" kann ich mir keine Zigarette drehen");
                    try {
                        topf.wait();
                    } catch (InterruptedException e) {
//                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    private void rauchen() {
        try {
            System.out.println(name +": zündet sich seine Zigarette an ....");
            Thread.sleep(2700);
            System.out.println(name +": wirft die Zigarette auf den Boden");
            Thread.sleep(500);
            System.out.println(name +": ...stampf...");
            Thread.sleep(500);
            System.out.println(name +": ...stampf...");
            Thread.sleep(500);
            System.out.println(name +": is aus");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            Thread.currentThread().interrupt();
        }
    }

    private void rollen(List<Dinge> zutaten) {
        System.out.println(name +": Rollt sich seine Zigarette mit den Zutaten: "+zutaten+" und seinem "+ding);
        zutaten.clear();

    }

    private boolean alleGutenDingeSindDrei(List<Dinge> topf) {
        return !topf.contains(ding)&& !topf.isEmpty();
    }
}
