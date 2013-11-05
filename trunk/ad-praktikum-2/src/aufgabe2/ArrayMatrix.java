package aufgabe2;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander Breitenstein
 * Date: 31.10.13
 * Time: 11:25
 */
public class ArrayMatrix implements Matrix{

    private double[][] matrix;

    private ArrayMatrix() {
    }

    public static ArrayMatrix create() {
        return new ArrayMatrix();
    }

    @Override
    public void initialize(int dimension) {
        this.matrix = new double[dimension][dimension];
    }

    @Override
    public void add(Matrix matrix) {
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix.length; j++) {
                this.matrix[i][j] = this.matrix[i][j]+matrix.getElement(i,j);
            }
        }
    }

    @Override
    public void scalarMultiplication(double scalar) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = matrix[i][j]*scalar;
            }
        }
    }

    @Override
    public void matrixMultiplication(Matrix matrix) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void pow(double power) {

               // WRONG!!!!!! ->> use multiplikation in a loop for i = 0 to power

    }

    @Override
    public double getElement(int zeile, int spalte) {
        return matrix[zeile][spalte];
    }

    @Override
    public void setElement(int zeile, int spalte, double eleme) {
        matrix[zeile][spalte] = eleme;
    }
}