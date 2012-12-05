package semaphore;

import services.RandomManager;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
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

    private Queue<Student> studentQueue;
    public Semaphore S_ANSTELLEN = new Semaphore(0,true),
                     S_BEZAHLEN = new Semaphore(1,true);

    private Kasse(long id, int maxConncurrentRequests, int minVerkaufsZeit, int maxVerkaufsZeit) {
        this.id = id;
        this.minVerkaufsZeit = minVerkaufsZeit;
        this.maxVerkaufsZeit = maxVerkaufsZeit;
        this.studentQueue = new ArrayDeque<Student>();

    }

    public static Kasse create(long id) {
        //Standardwerte
        return new Kasse(id, default_maxConncurrentRequests, default_minVerkaufsZeit, default_maxVerkaufsZeit);
    }



    //Methods -@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    private void sleep(long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    //Methods with double underline -> System.out.print*
    private void __bezahl_info(Student student){
        System.out.println(student + " bezahlt an " + this);
    }

    private void __verlaesst_info(Student student){
        System.out.println(student + " verlaesst " + this);
    }

    private static void __anstell_info(Student student, Kasse kasse) {
        System.out.println(student + " stellt sich in die Warteschlange an " + kasse);
    }

    public void anstellen(Student student){
        __anstell_info(student, this);
        studentQueue.add(student);
    }

    public Student getStudentInLine(){
        return studentQueue.peek();
    }

    public void verlassen(){
        studentQueue.remove();
    }

    public int getLengthOfWarteSchlange(){
        return studentQueue.size();
    }

    private void bezahlen() throws InterruptedException {
        long verkaufsZeit = RandomManager.longNumber(maxVerkaufsZeit, minVerkaufsZeit);
        sleep(verkaufsZeit);
    }


    //Overrides - @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    @Override
    public String toString() {
        return "Kasse: " + id;
    }

    @Override
    public void run() {

        Thread kThread = Thread.currentThread();

        while(!kThread.isInterrupted()){

            try {
                S_ANSTELLEN.acquire();

                    S_BEZAHLEN.acquire();

                    Student student = getStudentInLine();

                    __bezahl_info(student);
                    bezahlen();

                    __verlaesst_info(student);
                    verlassen();

                S_BEZAHLEN.release();
                student.S_BEZAHLUNGFERTIG.release();
//                S_VERLASSEN.release();

            } catch (InterruptedException e) {
                kThread.interrupt();
                break;
            }

        }

    }
}
