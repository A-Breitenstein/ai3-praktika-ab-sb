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

    /**
     * Returns it own Dimension,
     * example : 5(5x5)
     *
     * @return
     */
    public int getDimension();

    /**
     * Returns how much Data is stored in the Matrix
     *
     * @return
     */
    public int quantityOfSavedElements();
}
