package aufgabe2;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander Breitenstein
 * Date: 08.11.13
 * Time: 08:56
 */
public class MatrixElemZeileSpalte<E> {

    private int zeile;

    private int spalte;

    private E element;

    private MatrixElemZeileSpalte(int zeile, int spalte, E element) {
        this.zeile = zeile;
        this.spalte = spalte;
        this.element = element;
    }

    public static <E> MatrixElemZeileSpalte<E> create(int zeile, int spalte, E element) {
        return new MatrixElemZeileSpalte<E>(zeile, spalte, element);
    }

    public int getZeile() {
        return zeile;
    }

    public int getSpalte() {
        return spalte;
    }

    public E getElement() {
        return element;
    }

    @Override
    public String toString() {
        return "{" + zeile +
                "," + spalte +
                "," + element +
                '}';
    }
}