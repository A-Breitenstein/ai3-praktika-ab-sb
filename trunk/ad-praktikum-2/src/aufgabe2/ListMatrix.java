package aufgabe2;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander Breitenstein
 * Date: 31.10.13
 * Time: 11:26
 */
public class ListMatrix implements Matrix{

    private List<MatrixElemZeileSpalte> matrix;

    private int dimension;

    private ListMatrix(int dimension) {
        this.dimension = dimension;
        this.initialize(dimension);
    }

    public static ListMatrix create(int dimension) {
        return new ListMatrix(dimension);
    }

    @Override
    public void initialize(int dimension) {
        this.matrix = new ArrayList<MatrixElemZeileSpalte>();
        this.dimension = dimension;
    }

    @Override
    public double getElement(int zeile, int spalte) {
        final int index = find(zeile, spalte, this.matrix);
        double elem = 0;

        if (index >= 0) {
            elem = (Double)this.matrix.get(index).getElement();
        }

        return elem;
    }

    @Override
    public void setElement(int zeile, int spalte, double eleme) {
        final int index = find(zeile, spalte, this.matrix);

        if (eleme != 0) {

            if (index >= 0) {
                this.matrix.remove(index);
            }

            MatrixElemZeileSpalte<Double> elemZeileSpalte = MatrixElemZeileSpalte.create(zeile, spalte, eleme);
            this.matrix.add(elemZeileSpalte);
        }
    }

    @Override
    public int getDimension() {
        return dimension;
    }

    @Override
    public int quantityOfSavedElements() {
        return matrix.size();
    }

    /**
     * Additional Searchfunction for an Element
     *
     * @param zeile
     * @param spalte
     * @param matrixElemZeileSpaltes
     * @return Index of Element, if not containing then it returns -1
     */
    private int find(int zeile, int spalte, List<MatrixElemZeileSpalte> matrixElemZeileSpaltes) {


        boolean searchning = true;
        ListIterator<MatrixElemZeileSpalte> iterator = matrixElemZeileSpaltes.listIterator();
        int index = -1;
        MatrixElemZeileSpalte temp;

        while (searchning && iterator.hasNext()) {
            temp = iterator.next();

            if (temp.getZeile() == zeile && temp.getSpalte() == spalte) {
                index = iterator.previousIndex();
                searchning = false;
            }
        }
        return index;
    }

    @Override
    public String toString() {
        return "ListMatrix{" +
                "matrix=" + matrix +
                '}';
    }
}