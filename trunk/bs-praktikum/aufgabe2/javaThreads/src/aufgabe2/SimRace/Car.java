package aufgabe2.SimRace;

import services.RandomManager;

/**
 * Created with IntelliJ IDEA.
 * Date: 12.11.12
 * Time: 22:31
 */
public class Car implements Runnable {

    public long id;
    public long rounds;
    long summedRoundTime;

    private Car(long id, long rounds) {
        this.id = id;
        this.rounds = rounds;
    }

    public static Car create(long id) {
        return new Car(id,1);
    }

    public static Car create(long id, long rounds) {
        return new Car(id, rounds);
    }

    @Override
    public void run() {
        Thread cThread = Thread.currentThread();
        System.out.println("Hallo ich bin: " + cThread.getName() + ", jetzt bin ich Auto: " + this.id);
        summedRoundTime = 0;

        for (int i = 0; i < this.rounds; i++) {
            long roundTime = RandomManager.longNumber(10000,2000);

            try {
                Thread.sleep(roundTime);

                if(SimRace.crashed)
                    break;

//                System.out.println(cThread.getName() + "Auto :"+ this.id + " ist eine rundenZeit von" + roundTime + " in Runde: " + i + " gefahren!");
                System.out.println("Auto = " + id + " Runde = " + (i+1) + " Zeit = " + roundTime);
                summedRoundTime += roundTime;

            } catch (InterruptedException e) {
                System.out.println(cThread.getName() + " war zu ungeduldig und hat nicht gewartet!");
            }
        }

//        System.out.println(cThread.getName() + " ist fertig!");
//        System.out.println("Fertig: Auto = " + id + " mit einer StreckenZeit von: " + this.summedRoundTime);
    }

    @Override
    public String toString() {
        return "Auto: " + id + " Streckenzeit : " + this.summedRoundTime;
    }
}
