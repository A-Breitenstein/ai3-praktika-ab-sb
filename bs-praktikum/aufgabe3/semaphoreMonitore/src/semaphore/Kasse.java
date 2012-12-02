package semaphore;

import services.RandomManager;

import java.util.concurrent.Semaphore;

/**
 *  BS-Praktikum
 */
public class Kasse {
    private static int default_maxVerkaufsZeit = 15000,
                       default_minVerkaufsZeit = 5000,
                       default_maxConncurrentRequests = 1;

    private long id;
    private int minVerkaufsZeit, maxVerkaufsZeit;

    private int warteSchlange;
    private Semaphore semaphore;

    private Kasse(long id, int maxConncurrentRequests, int minVerkaufsZeit, int maxVerkaufsZeit) {
        this.id = id;
        this.semaphore = new Semaphore(maxConncurrentRequests,true);
        this.minVerkaufsZeit = minVerkaufsZeit;
        this.maxVerkaufsZeit = maxVerkaufsZeit;
        this.warteSchlange = 0;

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
        sleep(verkaufsZeit);

        __verlaesst_info(student);
        semaphore.release();

    }

    private void sleep(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    //Methods with double underline -> System.out.print*
    private void __bezahl_info(Student student){
        System.out.println(student + " bezahlt an " + this);
    }

    private void __verlaesst_info(Student student){
        System.out.println(student + " verlaesst " + this);
    }

    public void anstellen(){
        this.warteSchlange++;
    }

    public void verlassen(){
        this.warteSchlange--;
    }

    public int getWarteSchlange() {
        return warteSchlange;
    }

    //Overrides - @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


    @Override
    public String toString() {
        return "Kasse: " + id;
    }
}
