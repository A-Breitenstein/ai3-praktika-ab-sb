package aufgabe2.SimRace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Date: 12.11.12
 * Time: 22:31
 */
public class SimRace {

    static List<Car> carList = new ArrayList<Car>();
    static List<Thread> threadList = new ArrayList<Thread>();

    static long cars = 3, rounds = 5;
    static boolean crashed = false;
    static boolean finished = false;

    public static void main(String[] args) {
        for (int i = 0; i < cars; i++) {
            carList.add(Car.create(i,rounds));
        }

        for(Car car: carList){
            threadList.add(new Thread(car));
        }

        //Rennen wird gestartet!
        System.out.println("### Auf die Plaetze, fertig, Los ! ###");
        for(Thread t: threadList){
            t.start();
        }

        //Crash
        Crash crash = Crash.create(rounds);
        Thread crashThread = new Thread(crash);
        crashThread.start();

        //Join only needed, if Threads need to be finished before next process
        for(Thread t: threadList){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(crashed){

            System.out.println("Ein Unfall hat sich ereignet!\nDas Rennen wurde abgebrochen.");

        }else{

            finished = true;

            Comparator<Car> carComparator = new CarRangfolgenComparator();

            Collections.sort(carList, carComparator);

            System.out.println("Rangliste:");

            for(Car car: carList)
                System.out.println(car.toString());

        }
    }

    public static void setCrash() {
        crashed = true;
    }


}
