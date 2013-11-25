package aufgabe2;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander Breitenstein
 * Date: 31.10.13
 * Time: 11:25
 */
public class ArrayMatrix implements Matrix{

    private double[][] matrix;
    private int dimension;

    private ArrayMatrix() {
    }

    private ArrayMatrix(int dimension) {
        this.dimension = dimension;
        this.initialize(dimension);
    }

    public static ArrayMatrix create() {
        return new ArrayMatrix();
    }

    public static ArrayMatrix create(int dimension) {
        return new ArrayMatrix(dimension);
    }

    @Override
    public void initialize(int dimension) {
        this.dimension = dimension;
        this.matrix = new double[dimension][dimension];
    }

    @Override
    public double getElement(int zeile, int spalte) {
        return matrix[zeile][spalte];
    }

    @Override
    public void setElement(int zeile, int spalte, double elem) {
        matrix[zeile][spalte] = elem;
    }

    @Override
    public int getDimension() {
        return this.dimension;
    }

    @Override
    public int quantityOfSavedElements() {
        int counter = 0;

        for (double[] doubles : matrix) {
            for (double aDouble : doubles) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (double[] doubles : matrix) {
            stringBuilder.append(Arrays.toString(doubles));
            stringBuilder.append("\n");
        }

        return "ArrayMatrix{" +
                "\nmatrix=\n" + stringBuilder +
                ",\ndimension=" + dimension +
                '}';
    }
}