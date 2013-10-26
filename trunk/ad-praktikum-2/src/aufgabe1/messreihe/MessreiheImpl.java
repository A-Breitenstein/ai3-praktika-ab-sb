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

    private List<Double> listeVonMesswerte;

    private boolean newMittelwert;
    private boolean newVarianz;

    private MessreiheImpl() {
        this.listeVonMesswerte = new ArrayList<Double>();
        newMittelwert = true;
        newVarianz = true;
    }

    public static MessreiheImpl create() {
        return new MessreiheImpl();
    }

    @Override
    public void addMesswert(double messwert) {
        listeVonMesswerte.add(messwert);
        summedMesswerte += messwert;
        newMittelwert = true;
        newVarianz = true;
    }

    @Override
    public void addAllMesswert(double[] messwerte) {
        for (double messwert : messwerte) {
            listeVonMesswerte.add(messwert);
            summedMesswerte += messwert;
        }
        newMittelwert = true;
        newVarianz = true;
    }

    @Override
    public double calculateMittelwert() {
        if (newMittelwert)
            calculatedMittelwert = summedMesswerte / listeVonMesswerte.size();
        newMittelwert = false;


        return calculatedMittelwert;
    }

    @Override
    public double calculateVarianz() {
        if (newVarianz) {

            double varianzAccu = 0.0,
                    mittelwert = calculateMittelwert();
            for (Double aDouble : listeVonMesswerte) {
                varianzAccu += Math.pow(aDouble - mittelwert, 2.d);
            }

            calculatedVarianz = (varianzAccu / (listeVonMesswerte.size() - 1));
        }

        newVarianz = false;

        return calculatedVarianz;
    }

}