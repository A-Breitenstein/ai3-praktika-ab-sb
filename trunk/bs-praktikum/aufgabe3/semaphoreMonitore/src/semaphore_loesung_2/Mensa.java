package semaphore_loesung_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 04.12.12
 * Time: 16:01
 */
public class Mensa {
   public static List<Student> studentenDieEineKasseSuchen = new ArrayList<Student>();
   public  static List<Student> studenten = new ArrayList<Student>();
   public static List<Kasse> kassen = new ArrayList<Kasse>();
   public static ExecutorService threadPool = Executors.newCachedThreadPool();
   public static int studentCount = 10;
   public static Semaphore studentenDieSuchen = new Semaphore(studentCount-2,true);
   public static void init(){

       for (int i = 0; i < 3; i++) {
           kassen.add(new Kasse("Kasse"+i));
       }

       for (int i = 0; i < studentCount; i++) {
           studenten.add(new Student("Student" + i));
           studentenDieEineKasseSuchen.add(studenten.get(i));
       }
   }
    public static void studentSuchtEineKasse(Student student){
//        studentenDieSuchen.release();

        studentenDieEineKasseSuchen.add(student);
    }
    public static Kasse kuerzesteWarteschlangeBestimmen(){
        Kasse result = kassen.get(0);
        for (Kasse kasse : kassen) {
            if(kasse.getWarteschlangenlaenge() < result.getWarteschlangenlaenge()){
                result = kasse;
            }
        }
        return result;
    }
    public static Student getSuchendenStudenten(){
//        try {
//            studentenDieSuchen.acquire();
//        } catch (InterruptedException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
        Collections.shuffle(studentenDieEineKasseSuchen);
        Student result =   studentenDieEineKasseSuchen.get(0);
        studentenDieEineKasseSuchen = studentenDieEineKasseSuchen.subList(1,studentenDieEineKasseSuchen.size());
        return result;
    }
    public static void main(String[] args) {
        init();

        while (!studentenDieEineKasseSuchen.isEmpty()){
            threadPool.submit(getSuchendenStudenten().kasseZuweisen(kuerzesteWarteschlangeBestimmen()));
        }
        System.out.println("--ende--");
    }
}
