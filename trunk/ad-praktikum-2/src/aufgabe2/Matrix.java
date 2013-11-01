package aufgabe2;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander Breitenstein
 * Date: 31.10.13
 * Time: 09:06
 */
public interface Matrix {

    /**
     * Initialisiert die Matrix mit größe der angegebenen Dimension
     * Bei 0 wird keine Initialisierung vorgenommen
     * @param dimension
     */
    public void initialize(int dimension);

    /**
     * Addiert die Matrix und die im Parameter übergebene Matrix miteinander
     * Funktion verändert Objekt
     * @param matrix
     */
    public void add(Matrix matrix);

    /**
     * Multipliziert die Matrix mit dem angegeben Skalar
     * Funktion verändert Objekt
     * @param scalar
     */
    public void scalarMultiplication(double scalar);

    /**
     * Multipliziert die Matrix mit der angegebenen Matrix
     * Funktion verändert Objekt
     * @param matrix
     */
    public void matrixMultiplication(Matrix matrix);

    /**
     * Potenziert die Matrix mit dem angegebenen Wert
     * Funktion verändert Objekt
     * @param power
     */
    public void pow(double power);

    /**
     * Überschreibt Object:equals(Object object)
     *
     * @param object
     * @return
     */
    public boolean equals(Object object);

    /**
     * Holt das Element a[i,j] i = zeile, j = spalte, aus der Matrix
     *
     * @param zeile
     * @param spalte
     * @return
     */
    public double getElement(int zeile, int spalte);

    /**
     * Setzt das Element a[i,j] i = zeile, j = spalte, in der Matrix
     * @param zeile
     * @param spalte
     * @param eleme
     */
    public void setElement(int zeile, int spalte, double eleme);
}
