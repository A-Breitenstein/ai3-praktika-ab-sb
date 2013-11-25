package aufgabe2.test;

import aufgabe1.liste.Liste;
import aufgabe1.messreihe.Messreihe;
import aufgabe1.messreihe.MessreiheImpl;
import aufgabe2.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander Breitenstein
 * Date: 09.11.13
 * Time: 07:25
 */


public class MatrixUtilsTest {

    final int   DIMENSION = 20,
                ZERO = 0,
                UPPERRANGE = 10;

    @Test
    public void testAdd() throws Exception {
        Matrix arrayListMatrix  = ArrayListMatrix.create(DIMENSION);
        Matrix listMatrix       = ListMatrix.create(DIMENSION);

        MatrixUtils.randomFilling(arrayListMatrix,ZERO, UPPERRANGE);
        MatrixUtils.randomFilling(listMatrix,ZERO, UPPERRANGE);

        Matrix matrix = MatrixUtils.add(arrayListMatrix, listMatrix);

        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                assertTrue(matrix.getElement(i,j) == arrayListMatrix.getElement(i,j) + listMatrix.getElement(i,j) );
            }
        }
    }

    @Test
    public void testScalarMultiplication() throws Exception {
        Matrix arrayListMatrix  = ArrayListMatrix.create(DIMENSION);
        Matrix listMatrix       = ListMatrix.create(DIMENSION);
        Matrix arrayMatrix      = ArrayMatrix.create(DIMENSION);

        MatrixUtils.randomFilling(arrayMatrix,ZERO, UPPERRANGE);
        MatrixUtils.copyMatrix(arrayMatrix, listMatrix);
        MatrixUtils.copyMatrix(listMatrix,arrayListMatrix);

        double array         = arrayMatrix.getElement(0, 0),
               list          = listMatrix.getElement(0, 0),
               arrayList     = arrayListMatrix.getElement(0, 0);

        double scalar = 7;
        MatrixUtils.scalarMultiplication(arrayListMatrix,scalar);
        MatrixUtils.scalarMultiplication(listMatrix     ,scalar);
        MatrixUtils.scalarMultiplication(arrayMatrix    ,scalar);

        assertTrue(arrayMatrix.getElement(0, 0)     == array     * scalar);
        assertTrue(listMatrix.getElement(0, 0)      == list      * scalar);
        assertTrue(arrayListMatrix.getElement(0, 0) == arrayList * scalar);
    }

    //Test Von Aufgabe 6
    @Test
    public void testMatrixMultiplication() throws Exception {
        Matrix arrayListMatrixA  = ArrayListMatrix.create(DIMENSION);
        Matrix arrayListMatrixB  = ArrayListMatrix.create(DIMENSION);

        Matrix listMatrixA       = ListMatrix.create(DIMENSION);
        Matrix listMatrixB       = ListMatrix.create(DIMENSION);

        Matrix arrayMatrixA      = ArrayMatrix.create(DIMENSION);
        Matrix arrayMatrixB      = ArrayMatrix.create(DIMENSION);


        MatrixUtils.randomFilling(arrayListMatrixA,ZERO, UPPERRANGE);
        MatrixUtils.randomFilling(arrayListMatrixB,ZERO, UPPERRANGE);

        MatrixUtils.copyMatrix(arrayListMatrixA, listMatrixA);
        MatrixUtils.copyMatrix(arrayListMatrixA, arrayMatrixA);
        MatrixUtils.copyMatrix(arrayListMatrixB, listMatrixB);
        MatrixUtils.copyMatrix(arrayListMatrixB, arrayMatrixB);

        assertTrue(MatrixUtils.IsEqual(arrayListMatrixA,listMatrixA) && MatrixUtils.IsEqual(listMatrixA,arrayMatrixA));
        assertTrue(MatrixUtils.IsEqual(arrayListMatrixB,listMatrixB) && MatrixUtils.IsEqual(listMatrixB,arrayMatrixB));

        Matrix productOne = MatrixUtils.matrixMultiplication(arrayListMatrixA,arrayListMatrixB);
        Matrix productTwo = MatrixUtils.matrixMultiplication(listMatrixA, listMatrixB);
        Matrix productThree = MatrixUtils.matrixMultiplication(arrayMatrixA, arrayMatrixB);

        assertTrue(MatrixUtils.IsEqual(productOne,productTwo) && MatrixUtils.IsEqual(productTwo,productThree));

    }

    //Getestet mit : http://wims.unice.fr/~wims/wims.cgi
    @Test
    public void testPow() throws Exception {
        Matrix arrayListMatrix  = ArrayListMatrix.create(DIMENSION);
        Matrix listMatrix       = ListMatrix.create(DIMENSION);
        Matrix arrayMatrix      = ArrayMatrix.create(DIMENSION);

        MatrixUtils.randomFilling(arrayMatrix,ZERO, UPPERRANGE);
        MatrixUtils.copyMatrix(arrayMatrix, listMatrix);
        MatrixUtils.copyMatrix(listMatrix,arrayListMatrix);

        double array        = arrayMatrix.getElement(0, 0),
               list         = listMatrix.getElement(0, 0),
               arrayList    = arrayListMatrix.getElement(0, 0);

        double power = 3;
        System.out.println(arrayMatrix);

        arrayMatrix = MatrixUtils.pow(arrayMatrix,power);
        listMatrix = MatrixUtils.pow(listMatrix,power);
        arrayListMatrix = MatrixUtils.pow(arrayListMatrix,power);

        System.out.println(arrayMatrix);
    }

    @Test
    public void testIsEqual() throws Exception {

        Matrix arrayListMatrix  = ArrayListMatrix.create(DIMENSION);
        Matrix listMatrix       = ListMatrix.create(DIMENSION);

        MatrixUtils.randomFilling(arrayListMatrix,ZERO, UPPERRANGE);
        MatrixUtils.copyMatrix(arrayListMatrix,listMatrix);

        boolean isEqual = false;
        boolean cancelValidation = false;

        if (arrayListMatrix.getDimension() == listMatrix.getDimension()) {

            final int dimension = listMatrix.getDimension();

            for (int i = 0; i <dimension && !cancelValidation; i++) {
                for (int j = 0; j < dimension && !cancelValidation; j++) {
                    if (arrayListMatrix.getElement(i, j) != listMatrix.getElement(i, j)) {
                        isEqual = false;
                        cancelValidation = true;
                    }
                }
            }

            if (!cancelValidation)
                isEqual = true;

        }

        boolean EQ = isEqual == MatrixUtils.IsEqual(arrayListMatrix, listMatrix);

        assertTrue(EQ);
    }

    @Test
    public void testCopyMatrix() throws Exception {
        Matrix arrayListMatrix  = ArrayListMatrix.create(DIMENSION);
        Matrix listMatrix       = ListMatrix.create(DIMENSION);
        Matrix arrayMatrix      = ArrayMatrix.create(DIMENSION);

        MatrixUtils.randomFilling(arrayMatrix,ZERO, UPPERRANGE);
        MatrixUtils.copyMatrix(arrayMatrix, listMatrix);
        MatrixUtils.copyMatrix(listMatrix,arrayListMatrix);

        assertTrue(MatrixUtils.IsEqual(arrayMatrix,arrayListMatrix));
    }

    @Test
    public void testRandomFilling() throws Exception {
        Matrix arrayListMatrix  = ArrayListMatrix.create(DIMENSION);
        Matrix listMatrix       = ListMatrix.create(DIMENSION);
        Matrix arrayMatrix      = ArrayMatrix.create(DIMENSION);

        assertTrue(arrayListMatrix.quantityOfSavedElements() == 0);
        assertTrue(listMatrix.quantityOfSavedElements()      == 0);
        assertTrue(arrayMatrix.quantityOfSavedElements()     > 0);

        double sum = 0;

        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                sum += arrayMatrix.getElement(i, j);
            }
        }
        MatrixUtils.randomFilling(arrayListMatrix,ZERO, UPPERRANGE);
        MatrixUtils.randomFilling(listMatrix, ZERO, UPPERRANGE     );
        MatrixUtils.randomFilling(arrayMatrix,ZERO, UPPERRANGE    );

        assertTrue(arrayListMatrix.quantityOfSavedElements() > 0);
        assertTrue(listMatrix.quantityOfSavedElements()      > 0);
        assertTrue(arrayMatrix.quantityOfSavedElements()     > 0);

        double newSum = 0;

        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                newSum += arrayMatrix.getElement(i, j);
            }
        }

        assertFalse(sum == newSum);
    }

    @Test
    public void testFuerAufgabe7Und8() {

        //Messreihen
        Messreihe mr_speicher_ArrayListMatrix   = MessreiheImpl.create();
        Messreihe mr_speicher_ListMatrix        = MessreiheImpl.create();
        Messreihe mr_speicher_ArrayMatrix       = MessreiheImpl.create();

        Messreihe mr_zeit_ArrayListMatrix       = MessreiheImpl.create();
        Messreihe mr_zeit_ListMatrix            = MessreiheImpl.create();
        Messreihe mr_zeit_ArrayMatrix           = MessreiheImpl.create();

        long    timeBefore = 0,
                timeAfter = 0;

        //Matrikelnummer ist 2080222 -> n = 222
        final int n = 222,
                t = 20;

        //1% entspricht 100/100 -> ergiebt eine Zufallszahlenreihe von 0-99
        //5% -> 100/20 -> 0-19
        //10% -> 100/10 -> 0-9

        int[] p = {1, 5, 10};
        int[] p_upperRange = {99, 19, 9};
        int p_current, p_current_upperRange;

        Matrix arrayListMatrix = ArrayListMatrix.create(n);
        Matrix listMatrix = ListMatrix.create(n);
        Matrix arrayMatrix = ArrayMatrix.create(n);

        System.out.println("START LOOPS, Durchläufe : " + t + " Dimension: "+n);
        for (int i = 0; i < p.length; i++) {

            p_current = p[i];
            p_current_upperRange = p_upperRange[i];

            System.out.println("\nNeues P : " + p_current + " mit upperRange : " + p_current_upperRange);

            //Speicher bei identischem Inhalt
            for (int j = 0; j < t; j++) {
                System.out.println("Speicher-Durchlauf : " + (j + 1) + " für P = " + p_current);

                MatrixUtils.randomFilling(arrayListMatrix, ZERO, p_current_upperRange);
                MatrixUtils.copyMatrix(arrayListMatrix, listMatrix);
                MatrixUtils.copyMatrix(listMatrix, arrayMatrix);

                mr_speicher_ArrayListMatrix.addMesswert(arrayListMatrix.quantityOfSavedElements());
                mr_speicher_ListMatrix.addMesswert(listMatrix.quantityOfSavedElements());
                mr_speicher_ArrayMatrix.addMesswert(arrayMatrix.quantityOfSavedElements());

            }
            System.out.println("Speicherwerte:{MatrixTyp,Durchschnitt,Varianz}");
            System.out.println("ArrayListMatrix: " + mr_speicher_ArrayListMatrix.calculateMittelwert() + ", "+ mr_speicher_ArrayListMatrix.calculateVarianz());
            System.out.println("ListMatrix: " + mr_speicher_ListMatrix.calculateMittelwert() + ", "+ mr_speicher_ListMatrix.calculateVarianz());
            System.out.println("ArrayMatrix: " + mr_speicher_ArrayMatrix.calculateMittelwert() + ", "+ mr_speicher_ArrayMatrix.calculateVarianz());

            //Zeit bei unterschiedlichem Inhalt
            for (int j = 0; j < t; j++) {
                System.out.println("Zeit-Durchlauf : " + (j + 1) + " für P = " + p_current);

                //ArrayListMatrix
                timeBefore = System.currentTimeMillis();

                MatrixUtils.randomFilling(arrayListMatrix, ZERO, p_current_upperRange);

                timeAfter = System.currentTimeMillis() -timeBefore;
                mr_zeit_ArrayListMatrix.addMesswert(timeAfter);


                //ListMatrix
                timeBefore = System.currentTimeMillis();

                MatrixUtils.randomFilling(listMatrix, ZERO, p_current_upperRange);

                timeAfter = System.currentTimeMillis() -timeBefore;
                mr_zeit_ListMatrix.addMesswert(timeAfter);

                //ArrayMatrix
                timeBefore = System.currentTimeMillis();

                MatrixUtils.randomFilling(arrayMatrix, ZERO, p_current_upperRange);

                timeAfter = System.currentTimeMillis() -timeBefore;
                mr_zeit_ArrayMatrix.addMesswert(timeAfter);
            }

            System.out.println("Zeitwerte:{MatrixTyp,Durchschnitt,Varianz}");
            System.out.println("ArrayListMatrix: " + mr_zeit_ArrayListMatrix.calculateMittelwert() + ", "+ mr_zeit_ArrayListMatrix.calculateVarianz());
            System.out.println("ListMatrix: " + mr_zeit_ListMatrix.calculateMittelwert() + ", "+ mr_zeit_ListMatrix.calculateVarianz());
            System.out.println("ArrayMatrix: " + mr_zeit_ArrayMatrix.calculateMittelwert() + ", "+ mr_zeit_ArrayMatrix.calculateVarianz());
        }

    }

}