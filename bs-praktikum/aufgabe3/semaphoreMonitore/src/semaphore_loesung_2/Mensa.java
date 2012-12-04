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
   public static Semaphore listenZugriffs_mutex = new Semaphore(1,true);
   public static Semaphore studentenAufDerSuche = new Semaphore(studentCount,true);
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
        try {
            listenZugriffs_mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        studentenDieEineKasseSuchen.add(student);
        listenZugriffs_mutex.release();

        studentenAufDerSuche.release();
    }
    public static Kasse kuerzesteWarteschlangeBestimmen(){
        Kasse result = kassen.get(0);
        for (Kasse kasse : kassen) {
            if(kasse.getWarteschlangenlaenge() < result.getWarteschlangenlaenge()){
                result = kasse;
            }
        }
        StringBuilder kassenuebersicht = new StringBuilder("Schlangenlaenge:: ");
        for (Kasse kasse : kassen) {
            kassenuebersicht.append(kasse.name()+": "+kasse.getWarteschlangenlaenge()+" ");
        }
        System.out.println(kassenuebersicht);

        return result;
    }
    public static Student getSuchendenStudenten(){
        try {
            listenZugriffs_mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Collections.shuffle(studentenDieEineKasseSuchen);
        Student result =   studentenDieEineKasseSuchen.get(0);
        studentenDieEineKasseSuchen = studentenDieEineKasseSuchen.subList(1,studentenDieEineKasseSuchen.size());
        listenZugriffs_mutex.release();
        return result;
    }
    public static void main(String[] args) {
        init();

        while (true){

            try {
                studentenAufDerSuche.acquire(); // <-- sorgt dafür, dass einem suchendem studenten eine kasse zugewiesen wird
                threadPool.submit(getSuchendenStudenten().kasseZuweisen(kuerzesteWarteschlangeBestimmen()));
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }
    }
}
