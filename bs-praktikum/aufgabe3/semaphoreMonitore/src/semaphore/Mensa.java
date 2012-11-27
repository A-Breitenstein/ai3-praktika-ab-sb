package semaphore;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Alexander Breitenstein silpion IT-Solutions GmbH
 *         Date: 27.11.12
 */
public class Mensa {
    private static Set<Kasse> kassen;
    private static Set<Student> students;
    private static int  numberOfKassen = 3,
                        numberOfStudents = 10;


    public static void main(String[] args) {
        kassen = new HashSet<Kasse>();

        for (int i = 0; i < numberOfKassen; i++) {
            kassen.add(Kasse.create(i));
        }

        for (int i = 0; i < numberOfStudents; i++) {
            students.add(Student.create(i));
        }

        ExecutorService executorService = Executors.newCachedThreadPool();

    }

    //Methods - @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    private static Kasse getKasse(){
        Kasse k = null;

        for (Kasse kasse : kassen) {
            k = kasse;
            break;
        }

        return k;
    }

    public static void bezahlen(Student student){

        try {
            getKasse().bezahlen(student);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
