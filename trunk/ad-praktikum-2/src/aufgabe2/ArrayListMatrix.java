package aufgabe2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander Breitenstein
 * Date: 31.10.13
 * Time: 11:26
 */
public class ArrayListMatrix implements Matrix{

    private List<MatrixElemSpalte<Double>>[] matrix;

    private int dimension;

    private ArrayListMatrix() {
    }

    public static ArrayListMatrix create() {
        return new ArrayListMatrix();
    }

    private ArrayListMatrix(int dimension) {
        this.dimension = dimension;
        this.initialize(dimension);
    }

    public static ArrayListMatrix create(int dimension) {
        return new ArrayListMatrix(dimension);
    }

    @Override
    public void initialize(int dimension) {
        this.dimension = dimension;
        this.matrix = new List[dimension];
        for (int i = 0; i < dimension; i++) {
            this.matrix[i] = new ArrayList<MatrixElemSpalte<Double>>();
        }
    }

    @Override
    public double getElement(int zeile, int spalte) {
        List<MatrixElemSpalte<Double>> elements = matrix[zeile];

        for (MatrixElemSpalte<Double> element : elements) {
            if(element.getIndex() == spalte)
                return element.getElement();
        }
        return 0;
    }

    @Override
    public void setElement(int zeile, int spalte, double elem) {
        List<MatrixElemSpalte<Double>> elements = matrix[zeile];
        boolean elementFound = false;

        for (MatrixElemSpalte<Double> element : elements) {
            if (element.getIndex() == spalte) {
                element.setElement(elem);
                elementFound = true;
            }
        }

        if (!elementFound && elem != 0) {
            elements.add(MatrixElemSpalte.create(spalte, elem));
        }
    }

    @Override
    public int getDimension() {
        return this.dimension;
    }

    @Override
    public int quantityOfSavedElements() {
        int counter = 0;

        for (List<MatrixElemSpalte<Double>> matrixElemSpaltes : matrix) {
            for (MatrixElemSpalte<Double> matrixElemSpalte : matrixElemSpaltes) {
                counter++;
            }
        }

        return counter;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (List<MatrixElemSpalte<Double>> matrixElemSpaltes : matrix) {

            stringBuilder.append(matrixElemSpaltes.toString());
            stringBuilder.append("\n");
        }


        return "ArrayMatrix{" +
                "\nmatrix=\n" + stringBuilder +
                ",\ndimension=" + dimension +
                '}';
    }
}