package semaphore;

import services.RandomManager;

import java.util.concurrent.Semaphore;

/**
 * @author Alexander Breitenstein silpion IT-Solutions GmbH
 *         Date: 27.11.12
 */
public class Kasse {
    private static int default_maxVerkaufsZeit = 15000,
                       default_minVerkaufsZeit = 5000,
                       default_maxConncurrentRequests = 1;

    private long id;
    private int minVerkaufsZeit, maxVerkaufsZeit;

    private Semaphore semaphore;

    private Kasse(long id, int maxConncurrentRequests, int minVerkaufsZeit, int maxVerkaufsZeit) {
        this.id = id;
        this.semaphore = new Semaphore(maxConncurrentRequests);
        this.minVerkaufsZeit = minVerkaufsZeit;
        this.maxVerkaufsZeit = maxVerkaufsZeit;

    }

    public static Kasse create(long id, int maxConncurrentRequests) {
        return new Kasse(id, maxConncurrentRequests, default_minVerkaufsZeit, default_maxVerkaufsZeit);
    }

    public static Kasse create(long id, int maxConncurrentRequests, int minVerkaufsZeit, int maxVerkaufsZeit) {
        return new Kasse(id, maxConncurrentRequests, minVerkaufsZeit, maxVerkaufsZeit);
    }

    public static Kasse create(long id, int minVerkaufsZeit, int maxVerkaufsZeit) {
        //Wenn nur eine Verkäuferin an der Kasse ist
        return new Kasse(id, default_maxConncurrentRequests, minVerkaufsZeit, maxVerkaufsZeit);
    }

    public static Kasse create(long id) {
        //Standardwerte
        return new Kasse(id, default_maxConncurrentRequests, default_minVerkaufsZeit, default_maxVerkaufsZeit);
    }



    //Methods -@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    public void bezahlen(Student student) throws InterruptedException {

        semaphore.acquire();

        __bezahl_info(student);

        long verkaufsZeit = RandomManager.longNumber(maxVerkaufsZeit,minVerkaufsZeit);
        Thread.sleep(verkaufsZeit);

        semaphore.release();

        __verlaesst_info(student);
    }

    //Methods with double underline -> System.out.print*
    private void __bezahl_info(Student student){
        System.out.println(student + " bezahlt an " + this);
    }

    private void __verlaesst_info(Student student){
        System.out.println(student + " verlaesst " + this);
    }

    //Overrides - @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


    @Override
    public String toString() {
        return "Kasse: " + id;
    }
}
