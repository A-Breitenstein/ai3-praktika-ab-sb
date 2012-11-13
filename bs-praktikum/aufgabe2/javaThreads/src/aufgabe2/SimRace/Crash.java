package aufgabe2.SimRace;

import services.RandomManager;

/**
 * AD-Praktikum
 * Team: 13
 * Date: 13.11.12
 * Time: 17:44
 */
public class Crash implements Runnable {

    private long rounds;

    private Crash(long rounds) {
        this.rounds = rounds;
    }

    public static Crash create(long rounds) {
        return new Crash(rounds);
    }

    @Override
    public void run() {
        long crashTime = RandomManager.longNumber(10000,5000)*rounds;

        try {
            Thread.sleep(crashTime);
            SimRace.setCrash();
            if(!SimRace.finished){
                System.out.println("Crash um " +crashTime);
            }else{
                System.out.println("Crash hätte sich um " +crashTime + " ereignet.");
            }
        } catch (InterruptedException e) {
            System.out.println("Der CrashGenerator schläft nicht!!!!!");
            e.printStackTrace();
        }
    }
}
