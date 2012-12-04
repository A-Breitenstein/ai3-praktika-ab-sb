package semaphore;

import services.RandomManager;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 *  BS-Praktikum
 */
public class Kasse implements Runnable{
    private static int default_maxVerkaufsZeit = 15000,
                       default_minVerkaufsZeit = 5000,
                       default_maxConncurrentRequests = 1;

    private long id;
    private int minVerkaufsZeit, maxVerkaufsZeit;
    private Queue schlange = new ArrayDeque();

    private int warteSchlange;
    private Semaphore semaphore;
    public Semaphore schlangeSemaphore = new Semaphore(Mensa.numberOfStudents,true);

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
//        schlangeSemaphore.release(1);
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

    public void anstellen(Student student){
        schlange.add(student);
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

    @Override
    public void run() {


    }
}
