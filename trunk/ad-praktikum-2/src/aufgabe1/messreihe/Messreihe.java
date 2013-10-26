package aufgabe1.messreihe;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander Breitenstein
 * Date: 21.10.13
 * Time: 16:00
 */
public interface Messreihe {

    public void addMesswert(double messwert);

    public void addAllMesswert(double[] messwerte);

    public double calculateMittelwert();

    public double calculateVarianz();
}
