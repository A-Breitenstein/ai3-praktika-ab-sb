package semaphore;

import services.RandomManager;

import java.text.DecimalFormat;
import java.util.concurrent.Callable;

/**
 * BS-Praktikum
 */
public class Student implements Callable {
    private static int default_maxEatTime = 25000,
                       default_minEatTime = 15000,
                       default_maxReturnTime = 15000,
                       default_minReturnTime = 10000;

    private long id;
    private int maxEatTime, minEatTime, maxReturnTime, minReturnTime;
    private static DecimalFormat df = new DecimalFormat("#.##");

    private Student(long id, int maxEatTime, int minEatTime, int maxReturnTime, int minReturnTime) {
        this.id = id;
        this.maxEatTime = maxEatTime;
        this.minEatTime = minEatTime;
        this.maxReturnTime = maxReturnTime;
        this.minReturnTime = minReturnTime;
    }

    public static Student create(long id, int maxEatTime, int minEatTime, int maxReturnTime, int minReturnTime) {
        return new Student(id, maxEatTime, minEatTime, maxReturnTime, minReturnTime);
    }

    public static Student create(long id) {
        return new Student(id, default_maxEatTime, default_minEatTime, default_maxReturnTime, default_minReturnTime);
    }

    //Methods - @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    private void essen(long milliSeconds) {
        sleep(milliSeconds);
    }

    private void warten(long milliSeconds){
        sleep(milliSeconds);
    }

    private void sleep(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void __ess_info(long essZeit){
        System.out.println(this + " isst gerade für " + df.format(millisecondsToSeconds(essZeit)) + " Sekunden.");
    }

    private void __warte_info(long warteZeit){
        System.out.println(this + " wartet " + df.format(millisecondsToSeconds(warteZeit)) +" Sekunden auf seinen nächsten Einkauf");
    }

    //Getter - @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    public long getId() {
        return id;
    }

    //Overrides - @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    @Override
    public Object call() throws Exception {
        long essZeit, warteZeit;
        while(!Thread.currentThread().isInterrupted()){
            essZeit = RandomManager.longNumber(maxEatTime,minEatTime);
            warteZeit = RandomManager.longNumber(maxReturnTime,minReturnTime);

            Mensa.bezahlen(this);

            //Essen fassen
            __ess_info(essZeit);
            essen(essZeit);

            //warten darauf bis der Student wieder an die Kasse kann
            __warte_info(warteZeit);
            warten(warteZeit);

        }

        return null;
    }

    @Override
    public String toString() {
        return "Student: " + id;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(this + " wird jetzt kaputt gemacht!");
        super.finalize();
    }

    //Conversions - @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    private double millisecondsToSeconds(long milliseconds){
        return (double)milliseconds /1000d;
    }

}
