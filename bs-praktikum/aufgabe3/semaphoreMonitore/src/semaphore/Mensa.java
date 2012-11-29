package semaphore;

import services.RandomManager;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * BS-Praktikum
 */
public class Mensa {
    private static List<Kasse> kassen;
    private static List<Student> students;
    final private static int  numberOfKassen = 3,
                              numberOfStudents = 10;
    final private static long programmWarteZeit = RandomManager.longNumber(15000,12750);
    final private static TimeUnit timeUnit = TimeUnit.MILLISECONDS;

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
        } catch (InterruptedException e) {

        }

        executorService.shutdownNow();
        while(!executorService.isTerminated()){
            try {
//                __thread_kill_loop();
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }


        __programm_end();
    }

    private static void __thread_kill_loop(){
        System.out.println("thread killing");
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
        Random random = new Random();
        Kasse k = kassen.get(random.nextInt(kassen.size()));

        return k;
    }

    public static void bezahlen(Student student){

        try {
            getKasse().bezahlen(student);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            String s = "suds";
        }

    }
}
