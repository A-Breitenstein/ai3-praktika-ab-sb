package aufgabe1.messreihe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander Breitenstein
 * Date: 21.10.13
 * Time: 16:07
 */
public class MessreiheImpl implements Messreihe {

    private double summedMesswerte;

    private double calculatedMittelwert;

    private double calculatedVarianz;

    private int numberOfMesswerte;

    private boolean newMittelwert;


    //Online Varianz:
    /**
     * Übernommen von Wikipedia
     */
    // SOURCE :http://en.wikipedia.org/wiki/Algorithms_for_calculating_variance#Online_algorithm
    //double n = 0; // Anzahl der Messwerte
    double mean = 0;
    double M2 = 0;
    double delta = 0;
    double variance = 0;

    private double online_variance(double[] data) {

        for (double x : data) {
            numberOfMesswerte = numberOfMesswerte + 1;
            delta = x - mean;
            mean = mean + delta / numberOfMesswerte;
            M2 = M2 + delta * (x - mean);

            variance = M2 / (numberOfMesswerte - 1);
        }

        return variance;
    }


    private MessreiheImpl() {
        this.numberOfMesswerte = 0;
        newMittelwert = true;
    }

    public static MessreiheImpl create() {
        return new MessreiheImpl();
    }

    @Override
    public void addMesswert(double messwert) {
        summedMesswerte += messwert;

        online_variance(new double[]{messwert});
        newMittelwert = true;
    }

    @Override
    public void addAllMesswert(double[] messwerte) {

        for (double messwert : messwerte)
            summedMesswerte += messwert;

        online_variance(messwerte);

        newMittelwert = true;
    }

    @Override
    public double calculateMittelwert() {

        if (newMittelwert)
            calculatedMittelwert = summedMesswerte / numberOfMesswerte;

        newMittelwert = false;

        return calculatedMittelwert;
    }

    @Override
    public double calculateVarianz() {
//  Alte Varianz laut Formel im Aufgabenblatt
//            double varianzAccu = 0.0,
//                    mittelwert = calculateMittelwert();
//            for (Double aDouble : listeVonMesswerte) {
//                varianzAccu += Math.pow(aDouble - mittelwert, 2.d);
//            }
//
//            calculatedVarianz = (varianzAccu / (listeVonMesswerte.size() - 1));
//

        calculatedVarianz = online_variance(new double[]{});

        return calculatedVarianz;
    }

}