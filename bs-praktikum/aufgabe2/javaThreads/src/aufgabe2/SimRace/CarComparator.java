package aufgabe2.SimRace;

import java.util.Comparator;

/**
 * AD-Praktikum
 * Team: 13
 * Date: 13.11.12
 * Time: 01:34
 */
public class CarComparator implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {

        if(o1.summedRoundTime > o2.summedRoundTime)
            return 1;

        if(o1.summedRoundTime < o2.summedRoundTime)
            return -1;

        return 0;
    }
}
