package aufgabe2;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander Breitenstein
 * Date: 08.11.13
 * Time: 07:42
 */
public class MatrixElemSpalte<E> {

    private int index;

    private E element;

    private MatrixElemSpalte(int index, E element) {
        this.index = index;
        this.element = element;
    }

    public static <E> MatrixElemSpalte<E> create(int index, E element) {
        return new MatrixElemSpalte<E>(index, element);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return "{"+ index + ", " + element + '}';
    }
}