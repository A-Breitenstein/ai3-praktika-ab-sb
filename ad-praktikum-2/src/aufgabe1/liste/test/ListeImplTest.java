package aufgabe1.liste.test;

import aufgabe1.liste.Liste;
import aufgabe1.liste.ListeImpl;
import aufgabe1.liste.TimeType;
import aufgabe1.messreihe.Messreihe;
import aufgabe1.messreihe.MessreiheImpl;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;


/**
 * Created with IntelliJ IDEA.
 * User: Alexander Breitenstein
 * Date: 25.10.13
 * Time: 00:17
 */
public class ListeImplTest {
    @Test
    public void testCons() throws Exception {
        Liste<Double> liste = new ListeImpl<Double>();

        assertEquals(0.0, liste.lenght(), 0.0);

        for (int i = 0; i < 10; i++) {
            liste.cons(new Double(i));
        }

        assertEquals(10.0, liste.lenght(), 0.0);
    }

    @Test
    public void testHead() throws Exception {
        Liste<Double> liste = new ListeImpl<Double>();

        assertEquals(0.0, liste.lenght(), 0.0);

        for (int i = 0; i < 10; i++) {
            liste.cons(new Double(i));
        }

        assertEquals(10.0, liste.lenght(), 0.0);

        liste.head();

        assertEquals(9.0, liste.lenght(), 0.0);

    }

    @Test
    public void testLenght() throws Exception {
        Liste<Double> liste = new ListeImpl<Double>();

        assertEquals(0.0, liste.lenght(), 0.0);

        liste.cons(4.5);

        assertEquals(1.0, liste.lenght(), 0.0);
    }

    @Test
    public void testIsEmpty() throws Exception {

        Liste<Double> liste = new ListeImpl<Double>();

        assertTrue(liste.isEmpty());

        liste.cons(4.5);

        assertFalse(liste.isEmpty());
    }

    @Test
    public void testInsert() throws Exception {

        Liste<Double> liste = new ListeImpl<Double>();

        assertEquals(0.0, liste.lenght(), 0.0);

        for (int i = 0; i < 10; i++) {
            liste.insert(new Double(i), 0);
        }

        assertEquals(10.0, liste.lenght(), 0.0);

        liste.insert(45.0, 2);

        assertEquals(11.0, liste.lenght(), 0.0);

        liste.insert(45.0, 13);

        assertEquals(12.0, liste.lenght(), 0.0);
    }

    @Test
    public void testAufgabe1_2Abschnitt6() {
        Messreihe outer_messreihe = MessreiheImpl.create();
        // t = Anzahl an Testdurchläufen
        int t = 10;
        for (int i = 0; i < t; i++) {
            Liste<Double> liste = new ListeImpl<Double>();
            Messreihe intern_messreihe = MessreiheImpl.create();


        int einzufügendeElemente = 1000;

        for (int j = 1; j <= einzufügendeElemente; j++) {
            liste.cons(new Double(j));
            intern_messreihe.addMesswert(liste.getTime(TimeType.NANO));
        }

        assertTrue(einzufügendeElemente == liste.lenght());
            outer_messreihe.addMesswert(intern_messreihe.calculateMittelwert());

            System.out.println("Durchgang: " + (i + 1) + ", MittelWert: " + intern_messreihe.calculateMittelwert());
            System.out.println("Durchgang: " + (i + 1) + ", Varianz: " + intern_messreihe.calculateVarianz());
        }

        System.out.println("Mittelwert für " + t + " durchläufe beträgt: " + outer_messreihe.calculateMittelwert());
        System.out.println("Varianz für " + t + " durchläufe beträgt: " + outer_messreihe.calculateVarianz());
    }

    @Test
    public void testAufgabe1_2Abschnitt7() {

        Messreihe outer_messreihe = MessreiheImpl.create();
        // t = Anzahl an Testdurchläufen
        int t = 10;
        for (int i = 0; i < t; i++) {
            Liste<Double> liste = new ListeImpl<Double>();
            Messreihe intern_messreihe = MessreiheImpl.create();

            int einzufügendeElemente = 1000;

            for (int j = 1; j <= einzufügendeElemente; j++) {
                liste.insert(new Double(j), einzufügendeElemente);
                intern_messreihe.addMesswert(liste.getTime(TimeType.NANO));
            }

            assertTrue(einzufügendeElemente == liste.lenght());

            outer_messreihe.addMesswert(intern_messreihe.calculateMittelwert());

            System.out.println("Durchgang: " + (i + 1) + ", MittelWert: " + intern_messreihe.calculateMittelwert());
            System.out.println("Durchgang: " + (i + 1) + ", Varianz: " + intern_messreihe.calculateVarianz());
        }

        System.out.println("Mittelwert für " + t + " durchläufe beträgt: " + outer_messreihe.calculateMittelwert());
        System.out.println("Varianz für " + t + " durchläufe beträgt: " + outer_messreihe.calculateVarianz());
    }

    @Test
    public void testAufgabe1_2Abschnitt8() {

        Messreihe outer_messreihe = MessreiheImpl.create();
        // t = Anzahl an Testdurchläufen
        int t = 10;
        for (int i = 0; i < t; i++) {
            Liste<Double> liste = new ListeImpl<Double>();
            Messreihe intern_messreihe = MessreiheImpl.create();


            int einzufügendeElemente = 1000;

            for (int j = 1; j <= einzufügendeElemente; j++) {
                liste.insert(new Double(j), new Random().nextInt(einzufügendeElemente));
                intern_messreihe.addMesswert(liste.getTime(TimeType.NANO));
            }

            assertTrue(einzufügendeElemente == liste.lenght());

            outer_messreihe.addMesswert(intern_messreihe.calculateMittelwert());

            System.out.println("Durchgang: " + (i + 1) + ", MittelWert: " + intern_messreihe.calculateMittelwert());
            System.out.println("Durchgang: " + (i + 1) + ", Varianz: " + intern_messreihe.calculateVarianz());
        }

        System.out.println("Mittelwert für " + t + " durchläufe beträgt: " + outer_messreihe.calculateMittelwert());
        System.out.println("Varianz für " + t + " durchläufe beträgt: " + outer_messreihe.calculateVarianz());

    }
}