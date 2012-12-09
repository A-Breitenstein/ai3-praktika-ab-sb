package services;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * User: Qiqi
 * Date: 09.12.12
 * Time: 01:42
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
