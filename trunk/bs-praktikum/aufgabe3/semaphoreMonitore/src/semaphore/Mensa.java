package semaphore;

import services.RandomManager;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * BS-Praktikum
 */
public class Mensa {
    private static List<Kasse> kassen;
    private static List<Student> students;
    final private static int  numberOfKassen = 3;
    public final static int numberOfStudents = 10;
    final private static long programmWarteZeit = RandomManager.longNumber(15000,127500);
    final private static TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    public static Semaphore kasseSuchen = new Semaphore(1,true);

    public static void main(String[] args) {

        __programm_info(programmWarteZeit);

        kassen = new ArrayList<Kasse>();
        students = new ArrayList<Student>();

        for (int i = 0; i < numberOfKassen; i++) {
            kassen.add(Kasse.create(i));
        }

        for (int i = 0; i < numberOfStudents; i++) {
            students.add(Student.create(i));
        }

        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            executorService.invokeAll((Collection)students,programmWarteZeit,timeUnit);
        } catch (InterruptedException ignored) {

        }

        executorService.shutdownNow();
        while(!executorService.isTerminated()){
            try {
//                __thread_kill_loop();
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("No interrupt possible!");
            }
        }


        __programm_end();
    }

    private static void __programm_end() {
        System.out.println("Einkauf ist beendet");
    }

    private static void __anstell_info(Student student, Kasse kasse) {
        System.out.println(student + " stellt sich in die Warteschlange an " + kasse);
    }

    private static void __programm_info(long programmWarteZeit) {
        long programmWarteZeitInSec = programmWarteZeit/1000,
             programmWarteZeitInSexInMin = programmWarteZeitInSec/60;
        System.out.println("Vorgegebene Programmlaufzeit beträgt: " + programmWarteZeitInSec + " sec -> " + programmWarteZeitInSexInMin + " min");
    }

    //Methods - @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    private static Kasse getKasse(){
        Kasse k = kassen.get(0);

        try {

            kasseSuchen.acquire();

            for (Kasse kasse : kassen) {
//                System.out.println(kasse + " warteschlangenlänge :" + kasse.getWarteSchlange());
                if(kasse.getWarteSchlange() < k.getWarteSchlange())
                    k = kasse;


            }

//            k.schlangeSemaphore.acquire();





        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }



        return k;
    }

    public static void bezahlen(Student student){

//        try {
            Kasse k = getKasse();
            k.anstellen(student);
            kasseSuchen.release();
            __anstell_info(student, k);

//            k.bezahlen();
            k.verlassen();
//        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
//        }

    }


}

/*
MENSA:

Mensa.P(kasseSuchen)
k = getKasse();

k.add(student);

k.V(bezahlen)

Mensa.V(kasseSuchen)
1,2
<-------------------------------
2,1
k.P(bezahlenFertig)

KASSE:

k.P(bezahlen)

s = k.getStudent
k.bezahlen(s)

k.V(bezahlenFertig)
-------------------------------------------------------------------------------------------------------
http://de.wikipedia.org/wiki/Erzeuger-Verbraucher-Problem
 */
