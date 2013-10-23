package aufgabe1.messreihe;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander Breitenstein
 * Date: 21.10.13
 * Time: 16:00
 */
public interface Messreihe {

    public void addMesswert(float messwert);

    public void addAllMesswert(float[] messwerte);

    public float calculateMittelwert();

    public float calculateVarianz();
}
