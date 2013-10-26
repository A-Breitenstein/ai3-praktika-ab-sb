package aufgabe1.messreihe;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander Breitenstein
 * Date: 21.10.13
 * Time: 16:00
 */
public interface Messreihe {

    /**
     * Fügt einen Messwert der Messreihe hinzu
     * @param messwert
     */
    public void addMesswert(double messwert);

    /**
     * Fügt ein Array von Messwerten der Messreihe hinzu
     * @param messwerte
     */
    public void addAllMesswert(double[] messwerte);

    /**
     * Berechnet den Mittelwert aller bisher hinzugefügten Messwerte
     * @return Mittelwert
     */
    public double calculateMittelwert();

    /**
     * Berechnet die Varianz alle bisher hinzugefügten Messwerte
     * @return Varianz
     */
    public double calculateVarianz();
}
