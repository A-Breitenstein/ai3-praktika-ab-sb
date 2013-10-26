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

    private List<Double> listeVonMesswerte;

    private MessreiheImpl() {
        this.listeVonMesswerte = new ArrayList<Double>();
    }

    public static MessreiheImpl create() {
        return new MessreiheImpl();
    }

    @Override
    public void addMesswert(double messwert) {
        listeVonMesswerte.add(messwert);
        summedMesswerte += messwert;
    }

    @Override
    public void addAllMesswert(double[] messwerte) {
        for (double messwert : messwerte) {
            listeVonMesswerte.add(messwert);
            summedMesswerte += messwert;
        }
    }

    @Override
    public double calculateMittelwert() {
        return summedMesswerte / listeVonMesswerte.size();
    }

    @Override
    public double calculateVarianz() {
        double varianzAccu = 0.0,
                mittelwert = calculateMittelwert();
        for (Double aDouble : listeVonMesswerte) {
            varianzAccu += Math.pow(aDouble - mittelwert, 2.d);
        }

        return (varianzAccu / (listeVonMesswerte.size() - 1));
    }

}