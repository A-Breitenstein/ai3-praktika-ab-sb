package aufgabe1.messreihe.test;

import aufgabe1.messreihe.Messreihe;
import aufgabe1.messreihe.MessreiheImpl;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * User: Alex
 * Date: 26.10.13
 * Time: 04:13
 */
public class MessreiheImplTest {

    @Test
    public void testAddMesswert() throws Exception {
        Messreihe messreihe = MessreiheImpl.create();

        messreihe.addMesswert(2.0);
        messreihe.addMesswert(4.0);

        assertEquals(messreihe.calculateMittelwert(),3.0, 0.0);

    }

    @Test
    public void testAddAllMesswert() throws Exception {
        Messreihe messreihe = MessreiheImpl.create();

        double[] messwerte = new double[]{2.0,4.0};
        messreihe.addAllMesswert(messwerte);

        assertEquals(messreihe.calculateMittelwert(),3.0, 0.0);
    }

    @Test
    public void testCalculateMittelwert() throws Exception {
        Messreihe messreihe = MessreiheImpl.create();

        double[] messwerte = new double[]{2.0,4.0,7.0,8.0,23.0};
        messreihe.addAllMesswert(messwerte);
        messreihe.addMesswert(6.0);

        assertEquals(messreihe.calculateMittelwert(),8.333, 0.001);
    }

    @Test
    public void testCalculateVarianz() throws Exception {
        Messreihe messreihe = MessreiheImpl.create();

        double[] messwerte = new double[]{2.0,4.0,7.0,8.0,2.0};
        messreihe.addAllMesswert(messwerte);
        messreihe.addMesswert(6.0);

        assertEquals(messreihe.calculateVarianz(),6.566, 0.001);
    }
}
