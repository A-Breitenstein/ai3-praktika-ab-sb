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
    private static List<Kasse> kassen = new ArrayList<Kasse>();
    private static List<Student> students = new ArrayList<Student>();
    private static List<Thread> threads = new ArrayList<Thread>();
    final private static int  numberOfKassen = 3;
    public final static int numberOfStudents = 10;
    final private static long programmWarteZeit = RandomManager.longNumber(15000,127500);

    public static Semaphore S_KASSESUCHEN = new Semaphore(1,true);

    public static void main(String[] args) {

        __programm_info(programmWarteZeit);

        for (int i = 0; i < numberOfKassen; i++) {
            Kasse kasse = Kasse.create(i);
            kassen.add(kasse);
            threads.add(new Thread(kasse));
        }

        for (int i = 0; i < numberOfStudents; i++) {
            Student student = Student.create(i);
            students.add(student);
            threads.add(new Thread(student));
        }


        ExecutorService executorService = Executors.newCachedThreadPool();

        for (Thread thread : threads) {
            executorService.submit(thread);
        }

        try {
            Thread.sleep(programmWarteZeit);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        executorService.shutdownNow();

//        while(!executorService.isTerminated()){
//            try {
////                __thread_kill_loop();
//                Thread.sleep(40);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                System.out.println("No interrupt possible!");
//            }
//        }


        __programm_end();
    }

    private static void __programm_end() {
        System.out.println("Einkauf ist beendet");
    }



    private static void __programm_info(long programmWarteZeit) {
        long programmWarteZeitInSec = programmWarteZeit/1000,
             programmWarteZeitInSexInMin = programmWarteZeitInSec/60;
        System.out.println("Vorgegebene Programmlaufzeit beträgt: " + programmWarteZeitInSec + " sec -> " + programmWarteZeitInSexInMin + " min");
    }

    //Methods - @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    private static Kasse getKasse(){
        Kasse k = kassen.get(0);

            for (Kasse kasse : kassen) {
//                System.out.println(kasse + " warteschlangenlänge :" + kasse.getWarteSchlangeCounter());
                if(kasse.getLengthOfWarteSchlange() < k.getLengthOfWarteSchlange())
                    k = kasse;

            }

        return k;
    }

    public static void anKasseAnstellen(Student student){


        try {
            S_KASSESUCHEN.acquire();

            Kasse kasse = getKasse();

            kasse.anstellen(student);

            kasse.S_ANSTELLEN.release(1);

//            kasse.S_VERLASSEN.acquire();

            S_KASSESUCHEN.release();


        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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

MENSA:
Semaphore: S_KASSESUCHEN(1)

P.S_KASSESUCHEN()

Kasse kasse = getKasseWithSmallestQueue();

kasse.anstellen(student); // und in die queu

kasse.V(S_ANSTELLEN(1))

V.S_KASSESUCHEN()

KASSE:
Semaphore: S_ANSTELLEN(N), S_BEZAHLEN(1)

P.S_ANSTELLEN(1)

P.S_BEZAHLEN()

student = getStudentAusQueu();

bezahlen(student);

V.S_BEZAHLEN()



 */
