package services;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Qiqi
 * Date: 09.12.12
 * Time: 01:42
 * To change this template use File | Settings | File Templates.
 */
public class RandomManagerTest {

    @Test
    public void test_doubleNumber() {
        double lowerB = 10d,
               upperB = 45d;

        int testRuns = 1000;
        double testNumber;

        for (int i = 0; i < testRuns; i++) {
            testNumber = RandomManager.doubleNumber(upperB, lowerB);
            assertTrue(testNumber >= lowerB || testNumber <= upperB);
//            System.out.println(testNumber);
        }
    }
}
