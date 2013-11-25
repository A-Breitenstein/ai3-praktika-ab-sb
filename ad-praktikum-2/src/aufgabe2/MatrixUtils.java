package aufgabe2;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander Breitenstein
 * Date: 08.11.13
 * Time: 09:08
 */
public class MatrixUtils {

    //Copyable
    /**
     * Addiert die Matrix und die im Parameter übergebene Matrix miteinander
     * Funktion gibt neue Matrix zurück
     * @param
     */
    public static Matrix add(Matrix matrixA, Matrix matrixB) {

        Matrix matrix = ListMatrix.create(matrixA.getDimension());
        for (int i = 0; i < matrixA.getDimension(); i++) {
            for (int j = 0; j < matrixB.getDimension(); j++) {
                matrix.setElement(i, j, matrixA.getElement(i, j) + matrixB.getElement(i, j));
            }
        }
        return matrix;
    }

    //Copyable
    /**
     * Multipliziert die Matrix mit dem angegeben Skalar
     * Funktion verändert Objekt
     * @param scalar
     */
    public static void scalarMultiplication(Matrix matrix, double scalar) {
        for (int i = 0; i < matrix.getDimension(); i++) {
            for (int j = 0; j < matrix.getDimension(); j++) {
                matrix.setElement(i,j,matrix.getElement(i,j)*scalar);
            }
        }
    }

    //Copyable
    /**
     * Multipliziert die Matrix mit der angegebenen Matrix
     * Gibt neue Matrix zurück
     */
    public static Matrix matrixMultiplication(Matrix matrixA, Matrix matrixB) {

        if (matrixA.getDimension() != matrixB.getDimension())
            throw new IllegalArgumentException("Matrix Dimension stimmen nicht überein");

        int dimension = matrixA.getDimension();

        Matrix newMatrix = ArrayMatrix.create(dimension);

        for(int i = 0;i < dimension;i++){
            for(int j = 0;j < dimension;j++){
                for(int k = 0;k < dimension;k++) {
                    newMatrix.setElement(i, j, newMatrix.getElement(i,j) + matrixA.getElement(i, k) * matrixB.getElement(k, j));
                }
            }
        }
        return newMatrix;
    }

    //Copyable
    /**
     * Potenziert die Matrix mit dem angegebenen Wert
     * Gibt neue Matrix zurück
     * @param power
     */
    public static Matrix pow(Matrix matrix, double power) {

        final Matrix oldMatrix = matrix;

        for (int i = 1; i < power; i++) {
            matrix = matrixMultiplication(matrix, oldMatrix);
        }

        return matrix;
    }


    /**
     * Überprüft MatrixA auf gleichheit mit MatrixB
     * @param matrixA
     * @param matrixB
     * @return
     */
    public static boolean IsEqual(Matrix matrixA, Matrix matrixB) {

        boolean isEqual = false;
        boolean cancelValidation = false;

        if (matrixA.getDimension() == matrixB.getDimension()) {

            final int dimension = matrixA.getDimension();

            for (int i = 0; i <dimension && !cancelValidation; i++) {
                for (int j = 0; j < dimension && !cancelValidation; j++) {
                    if (matrixA.getElement(i, j) != matrixB.getElement(i, j)) {
                        isEqual = false;
                        cancelValidation = true;
                    }
                }
            }

            if (!cancelValidation)
                isEqual = true;

        }

        return isEqual;
    }

    /**
     * Copies contetnt from source-Matrix to destination-Matrix
     *
     * @param source
     * @param destination
     */
    public static void copyMatrix(Matrix source, Matrix destination) {
        final int dimension = source.getDimension();

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                destination.setElement(i, j, source.getElement(i, j));
            }
        }
    }

    /**
     * Fills the matrix with random numbers, included in defined range
     *
     * Example : lowerRange = 0, upperRange = 4 -> numberrange:[0,1,2,3,4] each number has a 1/5 possibility
     *         : lowerRange = 2, upperRange = 11 -> numberrange:[2,3,4,5,6,7,8,9,10,11] each number has a 1/10 possibility
     *
     * @param matrix which is going to be filled with random numbers
     * @param lowerRange minimum number of range
     * @param upperRange maximum number of range
     */
    public static void randomFilling(Matrix matrix, int lowerRange, int upperRange) {

        if (upperRange <= lowerRange) throw new IllegalArgumentException("upperRange muss größer sein als lowerRange");

        final int matrixDimension = matrix.getDimension();

        final Random random = new Random();

        final int range = upperRange - lowerRange;

        for (int i = 0; i < matrixDimension; i++) {
            for (int j = 0; j < matrixDimension; j++) {
                matrix.setElement(i,j,random.nextInt(range)+lowerRange);
            }

        }
    }
}