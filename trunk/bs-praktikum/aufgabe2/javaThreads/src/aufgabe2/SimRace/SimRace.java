package aufgabe2.SimRace;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Date: 12.11.12
 * Time: 22:31
 */
public class SimRace {

    static List<Car> carList = new ArrayList<Car>();
    static List<Thread> threadList = new ArrayList<Thread>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            carList.add(new Car());
        }

        for(Car car: carList){
            threadList.add(new Thread(car));
        }

        for(Thread t: threadList){
            t.start();
        }

        for(Thread t: threadList){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

}
